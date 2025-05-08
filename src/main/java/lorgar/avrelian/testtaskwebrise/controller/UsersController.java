package lorgar.avrelian.testtaskwebrise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;
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
@Tag(name = "Пользователи", description = "Контроллер для управления данными пользователей")
public class UsersController {
    private final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;

    public UsersController(
            @Qualifier(value = "usersServiceImpl") UsersService usersService
    ) {
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
                                    array = @ArraySchema(schema = @Schema(implementation = UserNoSubscriptions.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<UserNoSubscriptions>> getAllUsers(
            @RequestParam(required = false) @Parameter(description = "Номер страницы", example = "1") Integer page,
            @RequestParam(required = false) @Parameter(description = "Размер страницы", example = "1") Integer size) {
        log.debug("Received request : GET /users with param values: page=" + page + " size=" + size);
        List<UserNoSubscriptions> all;
        if (page == null && size == null) {
            try {
                all = usersService.getAll();
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        } else if (page == null || size == null || page <= 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                all = usersService.getAll(page, size);
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
        if (all.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(all);
        }
    }

    @PostMapping
    @Operation(
            summary = "Создать",
            description = "Создать пользователя",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserNoSubscriptions.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    public ResponseEntity<UserNoSubscriptions> createUser(@RequestBody NewUserDTO user) {
        log.debug("Received request : POST /users with param values: user=" + user);
        UserNoSubscriptions userNoSubscriptions;
        try {
            userNoSubscriptions = usersService.createUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (userNoSubscriptions == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userNoSubscriptions);
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Найти",
            description = "Найти пользователя",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    public ResponseEntity<UserDTO> readUser(@PathVariable @Parameter(description = "ID пользователя", required = true, schema = @Schema(implementation = Long.class), example = "1") Long id) {
        log.debug("Received request : GET /users/" + id);
        if (id <= 0) return ResponseEntity.badRequest().build();
        UserDTO userDTO;
        try {
            userDTO = usersService.readUser(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (userDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userDTO);
    }
}
