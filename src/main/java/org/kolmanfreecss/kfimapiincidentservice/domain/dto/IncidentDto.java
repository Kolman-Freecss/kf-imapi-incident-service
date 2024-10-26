package org.kolmanfreecss.kfimapiincidentservice.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;

import java.util.Date;
import java.util.Optional;

/**
 * IncidentDto
 * Used to define the IncidentDto object.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
public record IncidentDto(@Schema(hidden = true) Long id,
                          @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String title,
                          @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String description,
                          @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED) Optional<Incident.Status> status,
                          @Schema(requiredMode = Schema.RequiredMode.REQUIRED) Incident.Priority priority,
                          @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED) Optional<Date> reportDate,
                          @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED) Optional<Date> resolutionDate) {
}
