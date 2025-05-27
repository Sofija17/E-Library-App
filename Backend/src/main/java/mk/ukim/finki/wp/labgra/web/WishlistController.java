package mk.ukim.finki.wp.labgra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.wp.labgra.dto.DisplayWishlistDto;
import mk.ukim.finki.wp.labgra.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Wishlists API", description = "Endpoints for managing user wishlists")
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @Operation(summary = "Get all wishlists", description = "Retrieves a list of all wishlists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wishlists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<DisplayWishlistDto> getAllWishlists(){
        return wishlistApplicationService.findAll();
    }

    @Operation(summary = "Get wishlists for a user", description = "Retrieves all wishlists belonging to a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wishlists for the user"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/{username}")
    public List<DisplayWishlistDto> getWishlistForUser(@PathVariable String username){
       return wishlistApplicationService.getWishlistsForUser(username);
    }

    @Operation(summary = "Add a book to a wishlist", description = "Adds a specific book to a given wishlist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully added to wishlist"),
            @ApiResponse(responseCode = "400", description = "Invalid wishlist or book ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{wishlistId}/add/{bookId}")
    public ResponseEntity<DisplayWishlistDto> addBookToWishlist(@PathVariable Long wishlistId, @PathVariable Long bookId){
        return wishlistApplicationService.addBookToWishlist(wishlistId, bookId)
                .map(wishlist -> ResponseEntity.ok(DisplayWishlistDto.from(wishlist)))
                .orElse(ResponseEntity.badRequest().build());
    }


    @Operation(summary = "Rent all books from wishlist", description = "Rents all available books from a wishlist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books successfully rented"),
            @ApiResponse(responseCode = "400", description = "Some books are unavailable for rent"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/rent/{wishlistId}")
    public ResponseEntity<String> rentAllBooks(@PathVariable Long wishlistId) {
        boolean success = wishlistApplicationService.rentBooksAllFromWishlist(wishlistId);

        if (success) {
            return ResponseEntity.ok("Book successfully rented!");
        } else {
            return ResponseEntity.badRequest().body("Error renting books!");
        }

    }

    @Operation(summary = "Delete a wishlist", description = "Deletes a wishlist by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Wishlist successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Wishlist not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        wishlistApplicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
