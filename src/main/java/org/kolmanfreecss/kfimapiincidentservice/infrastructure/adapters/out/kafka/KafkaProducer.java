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
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> sendIncident(final IncidentDto incidentDto) {
        try {
            final String jsonEvent = objectMapper.writeValueAsString(incidentDto);
            log.info("Producer produced the message {}", jsonEvent);
            
            return Mono.fromFuture(() -> kafkaTemplate.send(TOPIC, jsonEvent))
                    .doOnSuccess(recordMetadata -> log.info("Message produced successfully"))
                    .doOnError(exception -> log.error("Error while producing the message", exception))
                    .then();
        } catch (JsonProcessingException e) {
            log.error("Error while producing the message", e);
            return Mono.error(e);
        }
    }

}