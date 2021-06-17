package org.ifinalframework.javadoc.model.jackson;

import org.springframework.util.ClassUtils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * ClassJsonDeserializer.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassJsonDeserializer extends JsonDeserializer<Class> {

    @Override
    public Class deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        try {
            return ClassUtils.forName(jsonParser.getValueAsString(), getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
