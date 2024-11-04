package org.kolmanfreecss.kfimapiincidentservice.shared.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

/**
 * JacksonConfig
 * Used to define the JacksonConfig object.
 *
 * @author Kolman-Freecss
 * @version 1.0
 */
public class JacksonOptionalSerializer extends JsonSerializer<Optional> {

    @Override
    public void serialize(Optional optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (optional.isPresent()) {
            jsonGenerator.writeObject(optional.get());
        } else {
            jsonGenerator.writeNull();
        }
    }
}
