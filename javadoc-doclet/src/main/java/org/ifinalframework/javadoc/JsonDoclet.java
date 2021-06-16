package org.ifinalframework.javadoc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.RootDoc;
import lombok.extern.slf4j.Slf4j;

/**
 * JsonDoclet.
 *
 * @author likly
 * @version 1.0.0
 * @see com.sun.javadoc.Doclet
 * @since 1.0.0
 */
@Slf4j
public class JsonDoclet {

    private static final ObjectWriter JSON = new ObjectMapper()
        .registerModule(new JavaDocModule())
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .writerWithDefaultPrettyPrinter();

    /**
     * 生成JavaDoc的入口
     *
     * @param rootDoc 根文档
     * @return 处理结果
     */
    public static boolean start(RootDoc rootDoc) throws IOException {

        logger.info("开始处理JavaDoc...");

        for (final PackageDoc packageDoc : rootDoc.specifiedPackages()) {
            start(packageDoc);
        }

        logger.info("处理JavaDoc完成...");

        return true;
    }

    private static void start(PackageDoc packageDoc) throws IOException {
        logger.info("处理Package: {}", packageDoc.name());

        final String dirs = packageDoc.name().replace(".", "/");

        File file = new File(dirs);
        if (file.mkdirs()) {
            logger.info("创建Package： {}", dirs);
        }

        for (final ClassDoc classDoc : packageDoc.allClasses()) {
            start(dirs, classDoc);
        }

    }

    private static void start(String packagePath, ClassDoc classDoc) throws IOException {
        logger.info("开始处理源文件：{}", classDoc.qualifiedTypeName());

        final String javaDocPath = String
            .format("%s.json", classDoc.name().replace(".", "$"));
//
        write(packagePath, javaDocPath, classDoc);

    }

    private static void write(String path, String file, Doc doc) {
        try {
            final FileWriter writer = new FileWriter(new File(path, file));
            writer.write(JSON.writeValueAsString(doc));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static LanguageVersion languageVersion() {
        return LanguageVersion.JAVA_1_5;
    }

}
