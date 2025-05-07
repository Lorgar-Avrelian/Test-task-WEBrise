package lorgar.avrelian.testtaskwebrise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lorgar.avrelian.testtaskwebrise.model.User;
import lorgar.avrelian.testtaskwebrise.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
@RestController
@RequestMapping(path = "/users")
public class UsersController {
    private final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;

    public UsersController(@Qualifier(value = "usersServiceImpl") UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    @Operation(
            summary = "Список",
            description = "Список всех пользователей",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<User>> getAllUsers() {
        log.debug("Received request to retrieve all users");
        List<User> all = usersService.getAll();
        if (all == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(all);
        }
    }

    @PostMapping
    @Operation(
            summary = "Создать",
            description = "Создание пользователя",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    public ResponseEntity<User> postUser(@RequestBody User user) {
        log.debug("Received user : " + user.toString());
        User created = usersService.createUser(user);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(created);
        }
    }
}
