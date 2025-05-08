package lorgar.avrelian.testtaskwebrise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.service.SubscriptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
@RestController
@RequestMapping(path = "/subscriptions")
@Tag(name = "Подписки", description = "Контроллер для управления данными подписок")
public class SubscriptionsController {
    private final Logger log = LoggerFactory.getLogger(SubscriptionsController.class);
    private final SubscriptionsService subscriptionsService;

    public SubscriptionsController(@Qualifier(value = "subscriptionsServiceImpl") SubscriptionsService subscriptionsService) {
        this.subscriptionsService = subscriptionsService;
    }

    @GetMapping
    @Operation(
            summary = "Список",
            description = "Список всех подписок",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionNoUsers.class))
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
    public ResponseEntity<List<SubscriptionNoUsers>> getAllUsers(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        log.debug("Received request : GET /subscriptions with param values: page=" + page + " size=" + size);
        List<SubscriptionNoUsers> all;
        if (page == null || size == null) {
            try {
                all = subscriptionsService.getAll();
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        } else if (page > 0 && size > 0) {
            try {
                all = subscriptionsService.getAll(page, size);
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
        if (all.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(all);
        }
    }
}
