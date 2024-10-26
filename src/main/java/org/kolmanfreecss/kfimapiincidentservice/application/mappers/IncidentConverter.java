package org.kolmanfreecss.kfimapiincidentservice.application.mappers;

import lombok.NoArgsConstructor;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * IncidentConverter
 * Used to convert the Incident entity to a DTO and vice versa.
 *
 * @version 1.0
 * @uthor Kolman-Freecss
 */
@NoArgsConstructor
@Component
public class IncidentConverter {

    public IncidentDto toDto(final Incident incident) {
        return new IncidentDto(incident.getId(), incident.getTitle(), incident.getDescription(),
                Optional.ofNullable(incident.getStatus()), incident.getPriority(), Optional.ofNullable(incident.getReportDate()), Optional.ofNullable(incident.getResolutionDate()));
    }

    public Incident toEntity(final IncidentDto incidentDto) {
        return new Incident(incidentDto.id(), 
                incidentDto.title(), 
                incidentDto.description(),
                incidentDto.status().orElse(null),
                incidentDto.priority(),
                incidentDto.reportDate().orElse(null),
                incidentDto.resolutionDate().orElse(null));
    }

}
