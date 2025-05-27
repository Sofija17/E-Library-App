package mk.ukim.finki.wp.labgra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.wp.labgra.dto.CreateBookDto;
import mk.ukim.finki.wp.labgra.dto.CreateCountryDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayCountryDto;
import mk.ukim.finki.wp.labgra.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Countries API", description = "Endpoints for managing countries")
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;


    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Lists all countries.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of countries"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Add a new country", description = "Creates and adds a new country.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Country successfully added"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto countryDto) {
        return countryApplicationService.save(countryDto)
                .map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a country", description = "Updates the details of an existing country.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country successfully updated"),
            @ApiResponse(responseCode = "404", description = "Country not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto countryDto) {
        return countryApplicationService.update(id, countryDto)
                .map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Country not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(countryApplicationService.findById(id).isPresent()){
            countryApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
