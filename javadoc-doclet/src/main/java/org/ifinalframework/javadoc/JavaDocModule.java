package org.ifinalframework.javadoc;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;

/**
 * JavaDocModule.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public class JavaDocModule extends SimpleModule {

    public JavaDocModule() {
        super("JavaDocModule");
        addSerializer(ClassDoc.class, new ClassDocJsonSerializer());
        addSerializer(FieldDoc.class, new FieldDocJsonSerializer());
        addSerializer(MethodDoc.class, new MethodDocJsonSerializer());
    }

}
