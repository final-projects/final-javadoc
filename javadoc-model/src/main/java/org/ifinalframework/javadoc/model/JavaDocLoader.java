package org.ifinalframework.javadoc.model;

import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JavaDocLoader.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
final class JavaDocLoader {

    private static final ObjectMapper loader = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Nullable
    public static ClassDoc load(Class<?> clazz) {
        try {

            final String packagePath = clazz.getPackage().getName().replace(".", "/");

            final String javaDocResource = "/META-INF/docs/json/" + packagePath + "/" + clazz.getSimpleName() + ".json";

            byte[] bytes = new byte[8 * 1024];

            final InputStream inputStream = clazz.getResourceAsStream(javaDocResource);

            if (Objects.isNull(inputStream)) {
                return null;
            }

            final int read = inputStream.read(bytes);

            final String json = new String(bytes, 0, read);

            return loader.readValue(json, ClassDoc.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
