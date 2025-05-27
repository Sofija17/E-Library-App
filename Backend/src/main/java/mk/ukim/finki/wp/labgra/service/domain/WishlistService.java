package mk.ukim.finki.wp.labgra.service.domain;

import mk.ukim.finki.wp.labgra.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<Wishlist> findAll();
    Optional<Wishlist> addBookToWishlist(Long wishlistId, Long bookId);
    void deleteById(Long id);
    boolean rentBooksAllFromWishlist(Long wishlistId);

    List<Wishlist> getWishlistsForUser (String username);

}
