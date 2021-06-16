package org.ifinalframework.javadoc.swagger.mvc.plugins;

import org.springframework.stereotype.Component;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

/**
 * ParameterJavaDocBuilderPlugin.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ParameterJavaDocBuilderPlugin implements ParameterBuilderPlugin {

    @Override
    public void apply(final ParameterContext parameterContext) {

    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return false;
    }

}
