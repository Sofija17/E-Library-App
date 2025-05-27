package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.domain.Wishlist;

import java.util.List;

public record CreateWishlistDto(
        List<Book> books,
        String username) {

    public static CreateWishlistDto from (Wishlist wishlist){
        return new CreateWishlistDto(
                wishlist.getBooks(),
                wishlist.getUser().getUsername()
        );
    }

    public Wishlist toWishlist (User user){
        return new Wishlist(books,user);
    }

}
