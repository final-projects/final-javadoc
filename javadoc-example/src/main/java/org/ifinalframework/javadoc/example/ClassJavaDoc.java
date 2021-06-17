package org.ifinalframework.javadoc.example;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassJavaDoc.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
public class ClassJavaDoc {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    private List<String> tags;

    private InnerClassJavaDoc innerClassJavaDoc;

    public void hello() {

    }

    /**
     * Hello
     *
     * @param world 世界
     */
    public void hello(String world) {
    }

    public void hello(String world, List<String> args) {
    }

    /**
     * InnerClassJavaDoc
     */
    public class InnerClassJavaDoc {


        public class Inner2ClassJavaDoc{

        }
    }

}
