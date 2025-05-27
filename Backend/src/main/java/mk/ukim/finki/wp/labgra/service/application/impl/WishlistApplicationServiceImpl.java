package mk.ukim.finki.wp.labgra.service.application.impl;

import mk.ukim.finki.wp.labgra.dto.DisplayWishlistDto;
import mk.ukim.finki.wp.labgra.model.domain.Wishlist;
import mk.ukim.finki.wp.labgra.service.application.WishlistApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.BookService;
import mk.ukim.finki.wp.labgra.service.domain.UserService;
import mk.ukim.finki.wp.labgra.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final BookService bookService;
    private final UserService userService;
    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(BookService bookService, UserService userService, WishlistService wishlistService) {
        this.bookService = bookService;
        this.userService = userService;
        this.wishlistService = wishlistService;
    }

    @Override
    public List<DisplayWishlistDto> findAll() {
        return DisplayWishlistDto.from(wishlistService.findAll());
    }

    @Override
    public Optional<Wishlist> addBookToWishlist(Long wishlistId, Long bookId) {
        return wishlistService.addBookToWishlist(wishlistId,bookId);
    }

    @Override
    public void deleteById(Long id) {
        wishlistService.deleteById(id);
    }

    @Override
    public boolean rentBooksAllFromWishlist(Long wishlistId) {
        return wishlistService.rentBooksAllFromWishlist(wishlistId);
    }

    @Override
    public List<DisplayWishlistDto> getWishlistsForUser(String username) {
        return wishlistService.getWishlistsForUser(username)
                .stream().map(DisplayWishlistDto::from).collect(Collectors.toList());
    }
}
