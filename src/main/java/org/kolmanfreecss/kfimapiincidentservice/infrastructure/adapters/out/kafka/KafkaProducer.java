package org.kolmanfreecss.kfimapiincidentservice.infrastructure.adapters.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolmanfreecss.kfimapiincidentservice.application.ports.IncidentEventHandlerPort;
import org.kolmanfreecss.kfimapiincidentservice.domain.dto.IncidentDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * KafkaProducer
 * Kafka producer to send messages to the Kafka topic.
 * @version 1.0
 * @uthor Kolman-Freecss
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer implements IncidentEventHandlerPort {
    
    public static final String TOPIC = "kf_imapi_incident_channel";
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Mono<Void> sendIncident(final IncidentDto incidentDto) {
        try {
            final String jsonEvent = new ObjectMapper().writeValueAsString(incidentDto);
            log.info("Producer produced the message {}", jsonEvent);
            return Mono.fromFuture(() -> kafkaTemplate.send(TOPIC, jsonEvent).whenComplete((recordMetadata, exception) -> {
                if (exception != null) {
                    log.error("Error while producing the message", exception);
                } else {
                    log.info("Message produced successfully");
                }
            })).then();
        } catch (JsonProcessingException e) {
            log.error("Error while producing the message", e);
            return Mono.error(e);
        }
        // write your handlers and post-processing logic, based on your use case
    }

}