package org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolmanfreecss.kfimapiincidentservice.application.services.IncidentService;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest.model.ResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * IncidentController
 * Used to define the methods that the IncidentController must implement.
 *
 * @version 1.0
 * @uthor Kolman-Freecss
 * @see IncidentService
 */
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/incident")
public class IncidentController {

    private final IncidentService incidentService;

    @Operation(summary = "Save Incident", description = "Save Incident by userDto")
    @ApiResponse(responseCode = "201", description = "Incident saved successfully")
    @ApiResponse(responseCode = "500", description = "Error saving Incident")
    @PostMapping("/")
    public Mono<ResponseWrapper<IncidentDto>> saveUser(final @RequestBody IncidentDto incidentDto) {
        return incidentService.create(incidentDto)
                .map(e -> new ResponseWrapper<>(e, "Incident saved successfully"))
                .onErrorResume(e -> {
                    log.error("Error saving incident", e);
                    return Mono.just(new ResponseWrapper<IncidentDto>(null, "Error saving Incident"));
                });
    }

}
