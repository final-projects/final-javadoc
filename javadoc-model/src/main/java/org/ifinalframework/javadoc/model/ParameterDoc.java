package org.ifinalframework.javadoc.model;

import org.ifinalframework.javadoc.model.jackson.ClassJsonDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * ParameterDoc.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
public class ParameterDoc extends Doc{
    private String name;
    private String summary;

    @JsonDeserialize(using = ClassJsonDeserializer.class)
    private Class<?> type;
}
