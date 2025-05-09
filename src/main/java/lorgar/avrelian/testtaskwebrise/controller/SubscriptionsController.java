package lorgar.avrelian.testtaskwebrise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.service.SubscriptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    public SubscriptionsController(
            @Qualifier(value = "subscriptionsServiceImpl") SubscriptionsService subscriptionsService
    ) {
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
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionDTO.class))
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
    public ResponseEntity<List<SubscriptionDTO>> getAllUsers(
            @RequestParam(required = false) @Parameter(description = "Номер страницы", example = "1") Integer page,
            @RequestParam(required = false) @Parameter(description = "Размер страницы", example = "1") Integer size) {
        log.debug("Received request : GET /subscriptions with param values: page=" + page + " size=" + size);
        List<SubscriptionDTO> all;
        if (page == null && size == null) {
            try {
                all = subscriptionsService.getAll();
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        } else if (page == null || size == null || page <= 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                all = subscriptionsService.getAll(page, size);
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
            description = "Создать подписку",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionDTO.class)
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
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody NewSubscriptionDTO subscription) {
        log.debug("Received request : POST /subscriptions with param values: subscription=" + subscription);
        SubscriptionDTO subscriptionDTO;
        try {
            subscriptionDTO = subscriptionsService.createSubscription(subscription);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (subscriptionDTO == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(subscriptionDTO);
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Найти",
            description = "Найти подписку",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionDTO.class)
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
    public ResponseEntity<SubscriptionDTO> readSubscription(@PathVariable @Parameter(description = "ID подписки", required = true, schema = @Schema(implementation = Long.class), example = "1") Long id) {
        log.debug("Received request : GET /subscriptions/" + id);
        if (id <= 0) return ResponseEntity.badRequest().build();
        SubscriptionDTO subscriptionDTO;
        try {
            subscriptionDTO = subscriptionsService.readSubscription(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (subscriptionDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subscriptionDTO);
    }

    @PutMapping(path = "/{id}")
    @Operation(
            summary = "Обновить",
            description = "Обновить данные подписки",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionDTO.class)
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
    public ResponseEntity<SubscriptionDTO> putSubscription(@PathVariable @Parameter(description = "ID подписки", required = true, schema = @Schema(implementation = Long.class), example = "1") Long id, @RequestBody NewSubscriptionDTO subscription) {
        log.debug("Received request : PUT /subscriptions/" + id + " with param values: subscription=" + subscription);
        if (id <= 0) return ResponseEntity.badRequest().build();
        SubscriptionDTO subscriptionDTO;
        try {
            subscriptionDTO = subscriptionsService.putSubscription(id, subscription);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (subscriptionDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subscriptionDTO);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Удалить",
            description = "Удалить подписку",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
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
    public ResponseEntity<?> deleteSubscription(@PathVariable @Parameter(description = "ID подписки", required = true, schema = @Schema(implementation = Long.class), example = "1") Long id) {
        log.debug("Received request : DELETE /subscriptions/" + id);
        if (id <= 0) return ResponseEntity.badRequest().build();
        SubscriptionDTO subscriptionDTO;
        try {
            subscriptionDTO = subscriptionsService.deleteSubscription(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (subscriptionDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/top")
    @Operation(
            summary = "Топ",
            description = "Топ 3 подписки",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionDTO.class))
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
    public ResponseEntity<Collection<SubscriptionDTO>> readSubscriptionsTop() {
        log.debug("Received request : GET /subscriptions/top");
        Collection<SubscriptionDTO> subscriptionNoUsers;
        try {
            subscriptionNoUsers = subscriptionsService.readSubscriptionsTop();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (subscriptionNoUsers == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subscriptionNoUsers);
    }
}
