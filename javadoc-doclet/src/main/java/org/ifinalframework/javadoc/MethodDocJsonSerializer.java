package org.ifinalframework.javadoc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;

/**
 * MethodDocJsonSerializer.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodDocJsonSerializer extends DocJsonSerializer<MethodDoc> {

    @Override
    public void serialize(final MethodDoc methodDoc, final JsonGenerator jsonGenerator,
        final SerializerProvider serializerProvider)
        throws IOException {

        jsonGenerator.writeStartObject();

        common(jsonGenerator, methodDoc);

        if (Objects.nonNull(methodDoc.overriddenClass())) {
            jsonGenerator.writeStringField("overriddenClass", methodDoc.overriddenClass().toString());
        }
        if (Objects.nonNull(methodDoc.overriddenMethod())) {
            jsonGenerator.writeStringField("overriddenMethod", methodDoc.overriddenMethod().toString());
        }

        jsonGenerator.writeFieldName("parameters");
        jsonGenerator.writeStartArray();

        final Map<String, ParamTag> paramTags = Arrays.stream(methodDoc.paramTags())
            .collect(Collectors.toMap(ParamTag::parameterName, Function.identity()));

        for (final Parameter parameter : methodDoc.parameters()) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("name", parameter.name());
            jsonGenerator.writeStringField("type", parameter.type().qualifiedTypeName());
            jsonGenerator.writeStringField("summary",
                Optional.ofNullable(paramTags.get(parameter.name())).map(ParamTag::parameterComment).orElse(null));

            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField("returnType", methodDoc.returnType().qualifiedTypeName());
        jsonGenerator.writeStringField("genericReturnType", methodDoc.returnType().toString());

        jsonGenerator.writeEndObject();

    }

}
