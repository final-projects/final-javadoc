package org.ifinalframework.javadoc.model;

import org.ifinalframework.javadoc.model.jackson.ClassJsonDeserializer;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * MethodDoc.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
public class MethodDoc extends Doc {

    /**
     * 方法签名
     */
    private String signature;

    /**
     * 参数列表
     */
    private List<ParameterDoc> parameterDocs;

    @JsonDeserialize(using = ClassJsonDeserializer.class)
    private Class<?> returnType;

}
