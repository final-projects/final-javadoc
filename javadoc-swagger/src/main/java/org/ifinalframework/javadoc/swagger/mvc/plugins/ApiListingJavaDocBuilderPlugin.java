package org.ifinalframework.javadoc.swagger.mvc.plugins;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import org.ifinalframework.javadoc.model.ClassDoc;

import java.util.Objects;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;

/**
 * ApiListingJavaDocBuilderPlugin.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ApiListingJavaDocBuilderPlugin implements ApiListingBuilderPlugin {

    @Override
    public void apply(final ApiListingContext apiListingContext) {
        final Class<?> controller = apiListingContext.getResourceGroup().getControllerClass().orElse(null);
        if (Objects.isNull(controller)) {
            return;
        }

        final ClassDoc doc = ClassDoc.load(controller);
        if (Objects.nonNull(doc) && StringUtils.hasText(doc.getSummary())) {
            apiListingContext.apiListingBuilder().description(doc.getSummary());
        }

    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return true;
    }

}
