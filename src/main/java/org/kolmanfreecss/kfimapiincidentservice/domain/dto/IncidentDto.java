package org.kolmanfreecss.kfimapiincidentservice.domain.dto;

import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;

import java.util.Date;

/**
 * IncidentDto
 * Used to define the IncidentDto object.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
public record IncidentDto(Long id, String title, String description,
                          Incident.Status status, Incident.Priority priority, Date reportDate, Date resolutionDate) {
}
