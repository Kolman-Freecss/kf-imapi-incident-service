package org.kolmanfreecss.kfimapiincidentservice.application.mappers;

import lombok.NoArgsConstructor;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import org.springframework.stereotype.Component;

/**
 * IncidentConverter
 * Used to convert the Incident entity to a DTO and vice versa.
 *
 * @version 1.0
 * @author Kolman-Freecss
 */
@NoArgsConstructor
@Component
public class IncidentConverter {

    public IncidentDto toDto(final Incident incident) {
        return new IncidentDto(incident.getId(), incident.getTitle(), incident.getDescription(),
                incident.getStatus(), incident.getPriority(), incident.getReportDate(), incident.getResolutionDate());
    }

    public Incident toEntity(final IncidentDto incidentDto) {
        return new Incident(incidentDto.id(), 
                incidentDto.title(), 
                incidentDto.description(),
                incidentDto.getStatus().orElse(null),
                incidentDto.priority(),
                incidentDto.getReportDate().orElse(null),
                incidentDto.getResolutionDate().orElse(null));
    }

}
