package org.ifinalframework.javadoc.model;

import java.util.List;

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

    private Class<?> returnType;

}
