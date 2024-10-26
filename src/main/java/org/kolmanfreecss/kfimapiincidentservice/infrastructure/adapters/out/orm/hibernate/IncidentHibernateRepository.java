package org.kolmanfreecss.kfimapiincidentservice.infrastructure.adapters.out.orm.hibernate;

import org.kolmanfreecss.kfimapiincidentservice.domain.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * IncidentHibernateRepository Hibernate Implementation
 * Used to define the methods that the IncidentHibernateRepository must implement.
 * 
 * @version 1.0
 */
@RepositoryRestResource(exported = false) // This annotation is used to disable the automatic prototype generation of the repository.
@Repository
public interface IncidentHibernateRepository extends JpaRepository<Incident, Long> {
}
