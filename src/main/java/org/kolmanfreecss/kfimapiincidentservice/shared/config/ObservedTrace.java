package org.kolmanfreecss.kfimapiincidentservice.shared.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ObservedTrace
 * Used to observe the trace of the application.
 *
 * @version 1.0
 * @author Kolman-Freecss
 */
@Configuration
public class ObservedTrace {
    
    // To have the @Observed support we need to register this aspect
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
    
}
