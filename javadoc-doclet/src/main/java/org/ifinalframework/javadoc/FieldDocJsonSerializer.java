package org.ifinalframework.javadoc;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;

/**
 * FieldDocJsonSerializer.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public class FieldDocJsonSerializer extends DocJsonSerializer<FieldDoc> {

    @Override
    public void serialize(final FieldDoc fieldDoc, final JsonGenerator jsonGenerator,
        final SerializerProvider serializerProvider)
        throws IOException {
        jsonGenerator.writeStartObject();
        common(jsonGenerator, fieldDoc);


        jsonGenerator.writeStringField("type", fieldDoc.type().qualifiedTypeName());
        jsonGenerator.writeStringField("genericType", fieldDoc.type().toString());

        jsonGenerator.writeEndObject();
    }

}
