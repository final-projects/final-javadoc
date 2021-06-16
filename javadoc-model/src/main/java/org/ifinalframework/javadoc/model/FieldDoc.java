package org.ifinalframework.javadoc.model;

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

    private Class<?> type;

}
