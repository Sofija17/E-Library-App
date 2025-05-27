package mk.ukim.finki.wp.labgra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.wp.labgra.dto.CreateAuthorDto;
import mk.ukim.finki.wp.labgra.dto.CreateBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookDto;
import mk.ukim.finki.wp.labgra.model.projections.AuthorProjection;
//import mk.ukim.finki.wp.labgra.model.views.AuthorsPerCountryView;
import mk.ukim.finki.wp.labgra.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Authors API", description = "Endpoints for managing authors")
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }


//    @Operation(summary = "Get number of authors per country", description = "Returns the number of authors grouped by country.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of authors per country"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @GetMapping("/by-country")
//    public List<AuthorsPerCountryView> getBooksByaAuthor(){
//        return authorApplicationService.findAllAuthorsPerCountry();
//    }

    @Operation(summary = "Get only names and surnames of all authors", description = "Returns names and surnames using projection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved names"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/names")
    public List<AuthorProjection> getAuthorNames() {
        return authorApplicationService.findAllAuthorNames();
    }

    @Operation(summary = "Get author by ID", description = "Finds a author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //get All
    @Operation(summary = "Get all authors", description = "Lists all authors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of authors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto authorDto) {
        return authorApplicationService.save(authorDto)
                .map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit an author", description = "Updates the details of an existing author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author successfully updated"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id, @RequestBody CreateAuthorDto authorDto) {
        return authorApplicationService.update(id, authorDto)
                .map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete an author", description = "Deletes an existing author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(authorApplicationService.findById(id).isPresent()){
            authorApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
