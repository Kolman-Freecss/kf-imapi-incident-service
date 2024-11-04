package org.kolmanfreecss.kfimapiincidentservice.application.services;

import io.micrometer.observation.annotation.Observed;
import org.kolmanfreecss.kfimapiincidentservice.application.mappers.IncidentConverter;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentEventHandlerPort;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentRepositoryPort;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Objects;

/**
 * IncidentService
 * Used to define the methods that the IncidentService must implement.
 * 
 * @author Kolman-Freecss
 * @version 1.0
 */
@Service
public class IncidentService {
    
    private final IncidentRepositoryPort incidentRepositoryPort;
    
    private final IncidentEventHandlerPort incidentEventHandlerPort;
    
    private final IncidentConverter incidentConverter;
    
    public IncidentService(@Qualifier("incidentHibernateRepositoryPortImpl") final IncidentRepositoryPort incidentRepositoryPort,
                           final IncidentEventHandlerPort incidentEventHandlerPort,
                           final IncidentConverter incidentConverter) {
        this.incidentRepositoryPort = incidentRepositoryPort;
        this.incidentEventHandlerPort = incidentEventHandlerPort;
        this.incidentConverter = incidentConverter;
    }
    
    public Mono<IncidentDto> create(final IncidentDto incidentDto) {
        return this.incidentRepositoryPort.create(this.incidentConverter.toEntity(incidentDto))
                .flatMap(entity -> Mono.just(this.incidentConverter.toDto(entity)))
                .doOnNext(dto -> Schedulers.parallel().schedule(() -> this.incidentEventHandlerPort.sendIncident(dto).subscribe()));
    }
    
    @Cacheable(value = "incidents", key = "#root.methodName")
    @Observed(name = "getAllIncidents",
            contextualName = "IncidentService",
            lowCardinalityKeyValues = {"getAllIncidents", "IncidentService"})
    public Mono<List<IncidentDto>> getAll() {
        return this.incidentRepositoryPort.getAll()
                .flatMap(incidents -> Mono.just(incidents.stream().map(incidentConverter::toDto).toList()));
    }
    
    public Mono<IncidentDto> getById(final Long id) {
        return this.incidentRepositoryPort.getById(id)
                .flatMap(incident -> Mono.just(Objects.requireNonNull(incident.map(incidentConverter::toDto).orElse(null))));
    }
    
    public Mono<IncidentDto> update(final IncidentDto incidentDto) {
        return this.incidentRepositoryPort.update(this.incidentConverter.toEntity(incidentDto))
                .flatMap(entity -> Mono.just(this.incidentConverter.toDto(entity)));
    }
    
    public Mono<Void> delete(final Long id) {
        return this.incidentRepositoryPort.delete(id);
    }
    
}
