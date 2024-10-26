package org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolmanfreecss.kfimapiincidentservice.application.services.IncidentService;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.kolmanfreecss.kfimapiincidentservice.infrastructure.rest.model.ResponseWrapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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
    
    @Operation(summary = "Get All Incidents", description = "Get all incidents")
    @ApiResponse(responseCode = "200", description = "Incidents retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Error retrieving incidents")
    @GetMapping("/")
    public Mono<ResponseWrapper<List<IncidentDto>>> getAllIncidents() {
        return incidentService.getAll()
                .map(e -> new ResponseWrapper<>(e, "Incidents retrieved successfully"))
                .onErrorResume(e -> {
                    log.error("Error retrieving incidents", e);
                    return Mono.just(new ResponseWrapper<List<IncidentDto>>(null, "Error retrieving incidents"));
                });
    }
    
    @Operation(summary = "Get Incident by id", description = "Get incident by the given incidentId")
    @ApiResponse(responseCode = "200", description = "Incident retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Error retrieving incident")
    @GetMapping("/{id}")
    public Mono<ResponseWrapper<IncidentDto>> getIncidentById(final @PathVariable Long id) {
        return incidentService.getById(id)
                .map(e -> new ResponseWrapper<>(e, "Incident retrieved successfully"))
                .onErrorResume(e -> {
                    log.error("Error retrieving incident", e);
                    return Mono.just(new ResponseWrapper<IncidentDto>(null, "Error retrieving incident"));
                });
    }
    
    @Operation(summary = "Update Incident", description = "Update Incident by userDto")
    @ApiResponse(responseCode = "200", description = "Incident updated successfully")
    @ApiResponse(responseCode = "500", description = "Error updating Incident")
    @PutMapping("/")
    public Mono<ResponseWrapper<IncidentDto>> updateIncident(final @RequestBody IncidentDto incidentDto) {
        return incidentService.update(incidentDto)
                .map(e -> new ResponseWrapper<>(e, "Incident updated successfully"))
                .onErrorResume(e -> {
                    log.error("Error updating incident", e);
                    return Mono.just(new ResponseWrapper<IncidentDto>(null, "Error updating Incident"));
                });
    }
    
    @Operation(summary = "Delete Incident", description = "Delete Incident by incidentId")
    @ApiResponse(responseCode = "200", description = "Incident deleted successfully")
    @ApiResponse(responseCode = "500", description = "Error deleting Incident")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteIncident(final @PathVariable Long id) {
        return incidentService.delete(id)
                .doOnSuccess(recordMetadata -> log.info("Incident deleted successfully"))
                .doOnError(exception -> log.error("Error while deleting the incident", exception))
                .then();
    }
    

}
