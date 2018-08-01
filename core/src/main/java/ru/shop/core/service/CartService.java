package ru.shop.core.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.core.exception.AlreadyExistsException;
import ru.shop.core.persistense.entity.Book;
import ru.shop.core.persistense.entity.Cart;
import ru.shop.core.persistense.entity.CartPK;
import ru.shop.core.persistense.entity.User;
import ru.shop.core.persistense.repository.BookRepository;
import ru.shop.core.persistense.repository.CartRepository;
import ru.shop.core.persistense.repository.UserRepository;
import ru.shop.core.service.dto.cart.BookInCartDTO;
import ru.shop.core.service.dto.cart.CartDTO;
import ru.shop.core.service.mapper.CartMapper;

import java.util.Collection;

@Service
public class CartService {
    private CartRepository cartRepository;

    private BookRepository bookRepository;
    private UserRepository userRepository;

    private CartMapper cartMapper;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Transactional
    public BookInCartDTO add(Integer bookId, Integer userId) throws NotFoundException {
        if (!bookRepository.existsById(bookId))
            throw new NotFoundException("Book with such id=" + bookId + " does not exist!");

        User user = userRepository.getOne(userId);
        Book book = bookRepository.getOne(bookId);

        CartPK pk = new CartPK(user, book);
        if (cartRepository.existsById(pk))
            throw new AlreadyExistsException("Book with id=" + bookId + " for this user already exist!");

        Cart cart = new Cart(pk);
        cart.setCount(1);

        return cartMapper.toDTO(cartRepository.save(cart));
    }

    @Transactional(readOnly = true)
    public CartDTO getCart(Integer userId) {
        Collection<Cart> carts = cartRepository.findAllByUserId(userId);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(userId);
        cartDTO.setItems(cartMapper.toDTOs(carts));
        return cartDTO;
    }

    @Transactional
    public BookInCartDTO update(Integer bookId, Integer userId, Integer count) throws NotFoundException {
        User user = userRepository.getOne(userId);
        Book book = bookRepository.getOne(bookId);

        CartPK pk = new CartPK(user, book);
        if (!cartRepository.existsById(pk))
            throw new NotFoundException("Product with bookId=" + bookId + " and userId=" + userId + " does not exist in cart!");

        if (count < 0 || count > 100)
            throw new IllegalArgumentException("Сount must be greater than 0 and less than 100");

        Cart cart = cartRepository.getOne(pk);
        cart.setCount(count);

        return cartMapper.toDTO(cartRepository.save(cart));
    }

    @Transactional
    public BookInCartDTO delete(Integer bookId, Integer userId) throws NotFoundException {
        User user = userRepository.getOne(userId);
        Book book = bookRepository.getOne(bookId);
        CartPK pk = new CartPK(user, book);
        if (!cartRepository.existsById(pk))
            throw new NotFoundException("Product with bookId=" + bookId + " and userId=" + userId + " does not exist in cart!");

        Cart cart = cartRepository.getOne(pk);
        cartRepository.delete(cart);

        return cartMapper.toDTO(cart);
    }

    @Transactional
    public void clear(Integer userId) {
        cartRepository.deleteAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Double getTotalPrice(Integer userId) {
        return cartRepository.getTotalPrice(userId).doubleValue();
    }
}