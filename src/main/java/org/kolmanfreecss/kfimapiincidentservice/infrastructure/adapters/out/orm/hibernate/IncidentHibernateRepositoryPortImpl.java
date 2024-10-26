package org.kolmanfreecss.kfimapiincidentservice.infrastructure.adapters.out.orm.hibernate;

import lombok.AllArgsConstructor;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentRepositoryPort;
import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;

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
        return Mono.fromCallable(() -> incidentHibernateRepository.save(incident))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<List<Incident>> getAll() {
        return Mono.just(incidentHibernateRepository.findAll());
    }

    @Override
    public Mono<Optional<Incident>> getById(final Long id) {
        return Mono.fromCallable(() -> incidentHibernateRepository.findById(id))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Incident> update(final Incident incident) {
        return Mono.fromCallable(() -> incidentHibernateRepository.save(incident))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> delete(final Long id) {
        return Mono.fromCallable(() -> {
            incidentHibernateRepository.deleteById(id);
            return null;
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
