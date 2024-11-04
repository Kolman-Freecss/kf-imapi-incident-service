package org.kolmanfreecss.kfimapiincidentservice.shared.config.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Optional;

/**
 * JacksonConfig
 * Used to define the JacksonConfig object.
 * 
 * @version 1.0
 * @author Kolman-Freecss
 */
public class JacksonOptionalDeserializer<T> extends JsonDeserializer<Optional<T>> {

    @Override
    public Optional<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        T value = ctxt.readValue(p, ctxt.getTypeFactory().constructType(Object.class));
        return Optional.ofNullable(value);
    }
}