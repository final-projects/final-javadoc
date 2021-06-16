package org.ifinalframework.javadoc;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.sun.javadoc.Doc;
import com.sun.javadoc.Tag;

/**
 * DocJsonSerializer.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class DocJsonSerializer<T extends Doc> extends JsonSerializer<T> {

    protected void common(JsonGenerator generator, T doc) throws IOException {
        generator.writeStringField("name", doc.name());
        generator.writeStringField("summary", Arrays.stream(doc.commentText().split("\n")).findFirst().orElse(null));
        generator.writeStringField("since", Arrays.stream(doc.tags("since")).findFirst().map(Tag::text).orElse(null));
        generator
            .writeStringField("version", Arrays.stream(doc.tags("version")).findFirst().map(Tag::text).orElse(null));
        generator.writeStringField("signature", doc.toString());
    }

}
