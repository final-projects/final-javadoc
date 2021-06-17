package org.ifinalframework.javadoc.model;

import org.ifinalframework.javadoc.model.jackson.ClassJsonDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * FieldDoc.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
public class FieldDoc extends Doc {

    @JsonDeserialize(using = ClassJsonDeserializer.class)
    private Class<?> type;

}
