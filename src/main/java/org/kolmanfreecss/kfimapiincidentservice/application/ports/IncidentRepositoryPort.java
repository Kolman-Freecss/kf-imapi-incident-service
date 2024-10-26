package org.kolmanfreecss.kfimapiincidentservice.application.ports;

import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import reactor.core.publisher.Mono;

/**
 * IncidentRepository
 * Used to define the methods that the IncidentService must implement.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
public interface IncidentRepositoryPort {
    
    Mono<Incident> create(final Incident incident);
    
}
