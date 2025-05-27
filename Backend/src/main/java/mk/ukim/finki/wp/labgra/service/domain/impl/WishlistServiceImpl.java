package mk.ukim.finki.wp.labgra.service.domain.impl;

import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.domain.Wishlist;
import mk.ukim.finki.wp.labgra.repository.BookRepository;
import mk.ukim.finki.wp.labgra.repository.UserRepository;
import mk.ukim.finki.wp.labgra.repository.WishlistRepository;
import mk.ukim.finki.wp.labgra.service.domain.WishlistService;
import org.springdoc.webmvc.ui.SwaggerResourceResolver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }

    @Override
    public Optional<Wishlist> addBookToWishlist(Long wishlistId, Long bookId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found!"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        if (book.getAvailableCopies() > 0) {
            wishlist.getBooks().add(book);
            return Optional.of(wishlistRepository.save(wishlist));
        } else {
            throw new RuntimeException("Book is not available for renting!");
        }

    }

    @Override
    public boolean rentBooksAllFromWishlist(Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        if (wishlist.getBooks().isEmpty()) {
            throw new RuntimeException("Wishlist is empty!");
        }

        for (Book book : wishlist.getBooks()) {
            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookRepository.save(book);
            } else {
                throw new RuntimeException("Some books are not available for renting!");
            }
        }
        wishlist.getBooks().clear();
        wishlistRepository.save(wishlist);
        return true;
    }


    @Override
    public List<Wishlist> getWishlistsForUser(String username) {
        User user = userRepository.findById(username)
            .orElseThrow(() -> new RuntimeException("User not found!"));

        List<Wishlist> userWishlists = wishlistRepository.findByUser(user);

        if (!userWishlists.isEmpty()) {
            return userWishlists;
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setBooks(new ArrayList<>());

        return Collections.singletonList(wishlistRepository.save(wishlist));
    }
    @Override
    public void deleteById(Long id) {
        wishlistRepository.deleteById(id);
    }
}
