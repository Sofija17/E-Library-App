package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.domain.Wishlist;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayWishlistDto (
        Long id,
        List<Book> books,
        String username
){

    public static DisplayWishlistDto from (Wishlist wishlist){
        return new DisplayWishlistDto(
                wishlist.getId(),
                wishlist.getBooks(),
                wishlist.getUser().getUsername()
        );
    }

    public Wishlist toWishlist (User user){
        return new Wishlist(id,books,user);
    }

    public static List<DisplayWishlistDto> from (List<Wishlist> wishlists){
        return wishlists.stream().map(wishlist -> DisplayWishlistDto.from(wishlist)).collect(Collectors.toList());
    }

}
