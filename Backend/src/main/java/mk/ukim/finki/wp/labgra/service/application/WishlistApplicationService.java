package mk.ukim.finki.wp.labgra.service.application;

import mk.ukim.finki.wp.labgra.dto.DisplayWishlistDto;
import mk.ukim.finki.wp.labgra.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {
    List<DisplayWishlistDto> findAll();
    Optional<Wishlist> addBookToWishlist(Long wishlistId, Long bookId);
    void deleteById(Long id);
    boolean rentBooksAllFromWishlist(Long wishlistId);

    List<DisplayWishlistDto> getWishlistsForUser (String username);
}
