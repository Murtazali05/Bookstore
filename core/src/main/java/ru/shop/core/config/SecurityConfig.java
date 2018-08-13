package ru.shop.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.*;
import ru.shop.core.security.NoRedirectStrategy;
import ru.shop.core.security.TokenAuthenticationFilter;
import ru.shop.core.security.TokenAuthenticationProvider;
import ru.shop.core.service.dto.error.ErrorDTO;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            new AndRequestMatcher(
                    new AntPathRequestMatcher("/api/**", "POST"),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/users/**", "POST"))
            ),
            new AntPathRequestMatcher("/api/**", "PUT"),
            new AntPathRequestMatcher("/api/**", "DELETE"),
            new AntPathRequestMatcher("/api/cart/**"),
            new AntPathRequestMatcher("/api/orders/**")
    );

    private static final RequestMatcher SELLER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/books/**", "POST"),
            new AntPathRequestMatcher("/api/books/**", "PUT"),
            new AntPathRequestMatcher("/api/books/**", "DELETE"),

            new AntPathRequestMatcher("/api/orders/**", "PUT"),
            new AntPathRequestMatcher("/api/order/**", "DELETE")
    );

    private static final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
            new AndRequestMatcher(
                    new AntPathRequestMatcher("/api/**", "PUT"),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/orders/**", "PUT"))
            ),
            new AndRequestMatcher(
                    new AntPathRequestMatcher("/api/**", "DELETE"),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/orders/**", "DELETE")),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/photo/**", "DELETE"))
            ),
            new AndRequestMatcher(
                    new AntPathRequestMatcher("/api/**", "POST"),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/orders/**", "POST")),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/photo/**", "POST")),
                    new NegatedRequestMatcher(new AntPathRequestMatcher("/api/users/**", "POST"))
            )
    );

    private TokenAuthenticationProvider provider;

    private ObjectMapper mapper;

    @Autowired
    public void setProvider(TokenAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .authenticationProvider(provider)
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)

                .authorizeRequests()
                .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
                .requestMatchers(SELLER_URLS).hasAnyRole("ADMIN", "SELLER")
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    @Bean
    public TokenAuthenticationFilter authenticationFilter() throws Exception {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());

        return filter;
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler successHandler() {
        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());
        return successHandler;
    }

    private AuthenticationFailureHandler failureHandler() {
        return ((request, response, exception) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), new ErrorDTO(exception.getClass().getSimpleName(), exception.getMessage()));
        });
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage()));
        };
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}