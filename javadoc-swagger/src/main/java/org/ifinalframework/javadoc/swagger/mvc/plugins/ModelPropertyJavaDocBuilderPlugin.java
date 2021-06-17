package org.ifinalframework.javadoc.swagger.mvc.plugins;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import org.ifinalframework.javadoc.model.ClassDoc;
import org.ifinalframework.javadoc.model.FieldDoc;

import java.lang.reflect.Field;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.PropertySpecificationBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

/**
 * ModelPropertyJavaDocBuilderPlugin.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class ModelPropertyJavaDocBuilderPlugin implements ModelPropertyBuilderPlugin {

    private static final Field NAME_FIELD = Objects
        .requireNonNull(ReflectionUtils.findField(PropertySpecificationBuilder.class, "name"));

    static {
        ReflectionUtils.makeAccessible(NAME_FIELD);
    }

    @Override
    public void apply(final ModelPropertyContext context) {

        final String name = (String) ReflectionUtils.getField(NAME_FIELD, context.getSpecificationBuilder());

        final Class<?> erasedType = context.getOwner().getType().getErasedType();

        final Field field = ReflectionUtils.findField(erasedType, name);

        if (Objects.isNull(field)) {
            return;
        }

        final ClassDoc classDoc = ClassDoc.load(field.getDeclaringClass());

        if (Objects.isNull(classDoc)) {
            return;
        }

        final FieldDoc fieldDoc = classDoc.getFieldDocs().get(name);
        if (Objects.nonNull(fieldDoc) && StringUtils.hasText(fieldDoc.getSummary())) {
            context.getSpecificationBuilder().description(fieldDoc.getSummary());
        }

    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return true;
    }

}
