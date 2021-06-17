package org.ifinalframework.javadoc.model;

import org.springframework.lang.NonNull;

import org.ifinalframework.javadoc.model.jackson.ClassJsonDeserializer;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassDoc.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
public class ClassDoc extends Doc {

    @NonNull
    @JsonProperty("class")
    @JsonDeserialize(using = ClassJsonDeserializer.class)
    private Class<?> type;

    private Map<String, FieldDoc> fieldDocs;

    private List<MethodDoc> methodDocs;

    public static ClassDoc load(Class<?> clazz) {
        return JavaDocLoader.load(clazz);
    }

    public MethodDoc getMethod(Method method) {
        for (final MethodDoc methodDoc : methodDocs) {

            final String[] split = method.toGenericString().split(" ");

            final String sign = split[split.length - 1];

            if (methodDoc.getSignature().replace(" ", "").equals(sign)) {
                return methodDoc;
            }

        }

        return null;
    }

}
