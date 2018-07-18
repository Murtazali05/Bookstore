package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shop.security.UserDetailsImpl;
import ru.shop.service.CartService;
import ru.shop.service.dto.cart.BookInCartDTO;
import ru.shop.service.dto.cart.CartDTO;

@RestController
@RequestMapping("/api/cart")
@Api(tags = "Cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartDTO get(@AuthenticationPrincipal UserDetailsImpl user){
        return cartService.getCart(user.getId());
    }

    @GetMapping("/price")
    public Double getTotalPrice(@AuthenticationPrincipal UserDetailsImpl user){
        return cartService.getTotalPrice(user.getId());
    }

    @PostMapping
    public BookInCartDTO add(@RequestParam("bookId") Integer bookId, @AuthenticationPrincipal UserDetailsImpl user) throws NotFoundException {
        return cartService.add(bookId, user.getId());
    }

    @PutMapping
    public BookInCartDTO update(@RequestParam("bookId") Integer bookId, @RequestParam("count") Integer count, @AuthenticationPrincipal UserDetailsImpl user) throws NotFoundException {
        return cartService.update(bookId, user.getId(), count);
    }

    @DeleteMapping("/{bookId}")
    public BookInCartDTO delete(@PathVariable("bookId") Integer bookId, @AuthenticationPrincipal UserDetailsImpl user) throws NotFoundException {
        return cartService.delete(bookId, user.getId());
    }

    @DeleteMapping
    public ResponseEntity<Void> clear(@AuthenticationPrincipal UserDetailsImpl user) {
        cartService.clear(user.getId());
        return ResponseEntity.noContent().build();
    }
}
