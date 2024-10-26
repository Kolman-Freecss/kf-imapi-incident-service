package org.kolmanfreecss.kfimapiincidentservice.application.services;

import org.kolmanfreecss.kfimapiincidentservice.application.mappers.IncidentConverter;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentEventHandlerPort;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentRepositoryPort;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * IncidentService
 * Used to define the methods that the IncidentService must implement.
 * 
 * @uthor Kolman-Freecss
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
    
}
