package mk.ukim.finki.wp.labgra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.wp.labgra.dto.CreateBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookHistoryDto;
//import mk.ukim.finki.wp.labgra.model.views.BooksPerAuthorView;
import mk.ukim.finki.wp.labgra.service.application.BookApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.BookService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//nov endpoint logovi od aplikacijata, lista od objekti so ke imaat username, token,
// issued at, expired at, za sekoj jwt token so e kreiran od strana na app


//http://localhost:9091/swagger-ui/index.html

@Tag(name = "Books API", description = "Endpoints for managing books")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final BookService bookService;
    //TODO
    //dodaj zavisnost od applicationservice

    public BookController(BookService bookService, BookApplicationService bookApplicationService, BookService bookService1) {
        this.bookApplicationService = bookApplicationService;
        this.bookService = bookService1;
    }

    @Operation(summary = "Get all books", description = "Lists all available books.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.findAll();
    }


//    @Operation(summary = "Get number of books by a given author", description = "Lists number of books from a given author.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @GetMapping("/by-author")
//    public List<BooksPerAuthorView> getBooksByaAuthor(){
//        return bookService.allBooksPerAuthors();
//    }


    @Operation(summary = "Get  hsitory for book", description = "Gives the edit history for a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "History successfully displayed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/history/{bookId}")
    public List<DisplayBookHistoryDto> findHistoryByBook(@PathVariable Long bookId){
         return this.bookApplicationService.findHistoryByBook(bookId);
    }

    @Operation(summary = "Add a new book", description = "Creates and adds a new book.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully added"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    // @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto bookDto) {
        return bookApplicationService.save(bookDto)
                .map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a book", description = "Updates the details of an existing book.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully updated"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
   // @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto bookDto) {
        return bookApplicationService.update(id, bookDto)
                .map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
   // @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(bookApplicationService.findById(id).isPresent()){
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Rent a book", description = "Allows renting a book by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully rented"),
            @ApiResponse(responseCode = "400", description = "No available copies to rent or user not found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PatchMapping("/rent/{bookId}")
    public ResponseEntity<String> rentBook(@PathVariable Long bookId) {
        boolean success = bookApplicationService.rentedBook(bookId);
        if (success) {
          //  Optional<DisplayBookDto> book = bookApplicationService.findById(bookId);
            return ResponseEntity.ok("Book successfully rented!");
        } else {
            return ResponseEntity.badRequest().body("No available copies to rent or user not found!");
        }
    }
    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
// @PreAuthorize("hasRole('LIBRARIAN')")
//    @PatchMapping("/rent/{bookId}/{userId}")
//    public ResponseEntity<String> rentBook(@PathVariable Long bookId, @PathVariable Long userId) {
//        boolean success = bookService.rentedBook(bookId,userId);
//        if (success) {
//            Book book = bookService.findById(bookId).get();
//            String renters = book.getUsersRented().stream()
//                    .map(User::getUsername)
//                    .collect(Collectors.joining(", "));
//            return ResponseEntity.ok("Book successfully rented by: " + renters);
//        } else {
//            return ResponseEntity.badRequest().body("No available copies to rent or user not found!");
//        }
//    }
