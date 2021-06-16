package org.ifinalframework.javadoc.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.ifinalframework.javadoc.model.ClassDoc;

import org.junit.jupiter.api.Test;

/**
 * ClassJavaDocTest.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
class ClassJavaDocTest {

    @Test
    void testClass(){
        final ClassDoc classDoc = ClassDoc.load(ClassJavaDoc.class);

        assertEquals(ClassJavaDoc.class,classDoc.getType());
    }

}
