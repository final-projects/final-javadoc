package org.ifinalframework.javadoc.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Doc.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
public class Doc {

    private String name;

    private String summary;

    private String deprecated;

    private String version;

    private String since;

}
