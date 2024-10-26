package org.kolmanfreecss.kfimapiincidentservice.application.ports;

import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * IncidentRepository
 * Used to define the methods that the IncidentService must implement.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
public interface IncidentRepositoryPort {
    
    Mono<Incident> create(final Incident incident);
    
    Mono<List<Incident>> getAll();
    
    Mono<Optional<Incident>> getById(final Long id);
    
    Mono<Incident> update(final Incident incident);
    
    Mono<Void> delete(final Long id);
    
}
