package ru.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import ru.shop.security.TokenAuthenticationFilter;
import ru.shop.security.TokenAuthenticationProvider;

import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.requireNonNull;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final RequestMatcher SWAGGER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/**"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/v2/api-docs")
    );

    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/**", "GET")
    );

    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(
            new OrRequestMatcher(PUBLIC_URLS, SWAGGER_URLS)
    );

    private TokenAuthenticationProvider provider;

    public SecurityConfig(TokenAuthenticationProvider provider) {
        super();
        this.provider = requireNonNull(provider);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PUBLIC_URLS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(provider)
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    @Bean
    public TokenAuthenticationFilter authenticationFilter() throws Exception {

        final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
//        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());

        return filter;
    }

//    private AuthenticationSuccessHandler successHandler() {
//        return new SimpleUrlAuthenticationSuccessHandler();
//    }


    private AuthenticationFailureHandler failureHandler() {
        return ((request, response, exception) -> {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        });
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        };
    }
//    @Bean
//    public TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
//        final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setAuthenticationSuccessHandler(successHandler());
//        return filter;
//    }
//
//    @Bean
//    public SimpleUrlAuthenticationSuccessHandler successHandler() {
//        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//        successHandler.setRedirectStrategy(new NoRedirectStrategy());
//        return successHandler;
//    }
//
//    /**
//     * Disable Spring boot automatic filter registration.
//     */
//    @Bean
//    FilterRegistrationBean disableAutoRegistration(TokenAuthenticationFilter filter) {
//        final FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
//
//    @Bean
//    AuthenticationEntryPoint forbiddenEntryPoint() {
//        return new HttpStatusEntryPoint(FORBIDDEN);
//    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
