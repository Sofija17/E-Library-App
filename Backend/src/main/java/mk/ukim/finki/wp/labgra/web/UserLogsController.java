package mk.ukim.finki.wp.labgra.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.wp.labgra.dto.DisplayBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayUserLogDto;
import mk.ukim.finki.wp.labgra.service.application.UserApplicationService;
import mk.ukim.finki.wp.labgra.service.application.UserLogsApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userlogs")
@Tag(name = "UserLogs API", description = "Endpoints managing user tokens")
public class UserLogsController {

    private final UserLogsApplicationService userLogsApplicationService;

    public UserLogsController(UserLogsApplicationService userLogsApplicationService) {
        this.userLogsApplicationService = userLogsApplicationService;
    }


    @Operation(summary = "Get all user logs", description = "Lists all user logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<DisplayUserLogDto> findAll() {
        return userLogsApplicationService.findAll();
    }

}
