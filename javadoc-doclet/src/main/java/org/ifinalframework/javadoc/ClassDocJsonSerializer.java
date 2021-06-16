package org.ifinalframework.javadoc;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;

/**
 * ClassDocSerializer.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassDocJsonSerializer extends DocJsonSerializer<ClassDoc> {

    @Override
    public void serialize(final ClassDoc classDoc, final JsonGenerator jsonGenerator,
        final SerializerProvider serializerProvider)
        throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("class", classDoc.qualifiedName());
        common(jsonGenerator, classDoc);

        fields(classDoc, jsonGenerator);
        methods(classDoc, jsonGenerator);

        jsonGenerator.writeEndObject();

    }

    private void fields(ClassDoc classDoc, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeFieldName("fieldDocs");
        jsonGenerator.writeStartObject();

        for (final FieldDoc field : classDoc.fields()) {
            jsonGenerator.writeObjectField(field.name(), field);
        }
        jsonGenerator.writeEndObject();
    }

    private void methods(ClassDoc classDoc, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeFieldName("methodDocs");
        jsonGenerator.writeStartArray();

        for (final MethodDoc method : classDoc.methods()) {
            jsonGenerator.writeObject(method);
        }

        jsonGenerator.writeEndArray();
    }

}
