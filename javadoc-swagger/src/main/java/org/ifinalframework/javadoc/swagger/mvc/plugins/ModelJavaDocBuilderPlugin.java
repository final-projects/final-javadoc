package org.ifinalframework.javadoc.swagger.mvc.plugins;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import org.ifinalframework.javadoc.model.ClassDoc;

import java.util.Objects;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;

/**
 * ModelJavaDocBuilderPlugin.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ModelJavaDocBuilderPlugin implements ModelBuilderPlugin {

    @Override
    public void apply(final ModelContext context) {
        final Class<?> erasedType = context.getType().getErasedType();
        final ClassDoc doc = ClassDoc.load(erasedType);
        if (Objects.nonNull(doc) && StringUtils.hasText(doc.getSummary())) {
            context.getBuilder().description(doc.getSummary());
            context.getModelSpecificationBuilder().facets(it -> it.description(doc.getSummary()));
        }

    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return true;
    }

}
