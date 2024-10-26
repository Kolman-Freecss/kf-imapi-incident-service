package org.kolmanfreecss.kfimapiincidentservice.infrastructure.adapters.out.orm.hibernate;

import lombok.AllArgsConstructor;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentRepositoryPort;
import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * IncidentHibernateRepository Hibernate Implementation
 * Used to define the methods that the IncidentHibernateRepository must implement.
 * 
 * @version 1.0
 * @uthor Kolman-Freecss
 */
@AllArgsConstructor
@Repository("incidentHibernateRepositoryPortImpl")
public class IncidentHibernateRepositoryPortImpl implements IncidentRepositoryPort {
    
    private final IncidentHibernateRepository incidentHibernateRepository;
    
    @Override
    public Mono<Incident> create(final Incident incident) {
        return Mono.just(incidentHibernateRepository.save(incident));
    }
}
