package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.security.UserDetailsImpl;
import ru.shop.core.service.CartService;
import ru.shop.core.service.dto.cart.BookInCartDTO;
import ru.shop.core.service.dto.cart.CartDTO;

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
    @ApiOperation("Получить корзину")
    public CartDTO get(@AuthenticationPrincipal UserDetailsImpl user){
        return cartService.getCart(user.getId());
    }

    @GetMapping("/price")
    @ApiOperation("Получить общую стоимость товаров в корзине")
    public Double getTotalPrice(@AuthenticationPrincipal UserDetailsImpl user){
        return cartService.getTotalPrice(user.getId());
    }

    @PostMapping
    @ApiOperation("Добавить книгу в корзину")
    public BookInCartDTO add(@RequestParam("bookId") Integer bookId, @AuthenticationPrincipal UserDetailsImpl user) throws NotFoundException {
        return cartService.add(bookId, user.getId());
    }

    @PutMapping
    @ApiOperation("Изменить количество книг в корзине по ID книги")
    public BookInCartDTO update(@RequestParam("bookId") Integer bookId, @RequestParam("count") Integer count, @AuthenticationPrincipal UserDetailsImpl user) throws NotFoundException {
        return cartService.update(bookId, user.getId(), count);
    }

    @DeleteMapping("/{bookId}")
    @ApiOperation("Удалить книгу из корзины")
    public BookInCartDTO delete(@PathVariable("bookId") Integer bookId, @AuthenticationPrincipal UserDetailsImpl user) throws NotFoundException {
        return cartService.delete(bookId, user.getId());
    }

    @DeleteMapping
    @ApiOperation("Очистить корзину")
    public ResponseEntity<Void> clear(@AuthenticationPrincipal UserDetailsImpl user) {
        cartService.clear(user.getId());
        return ResponseEntity.noContent().build();
    }
}