package org.kolmanfreecss.kfimapiincidentservice.shared.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.kolmanfreecss.kfimapiincidentservice.shared.config.json.JacksonOptionalDeserializer;
import org.kolmanfreecss.kfimapiincidentservice.shared.config.json.JacksonOptionalSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * JacksonConfig
 * Used to define the JacksonConfig object.
 *
 * @author Kolman-Freecss
 * @version 1.0
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    
    private ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Optional.class, new JacksonOptionalSerializer());
        module.addDeserializer(Optional.class, new JacksonOptionalDeserializer<>());
        objectMapper.registerModule(module);
        return objectMapper;
    }

}
