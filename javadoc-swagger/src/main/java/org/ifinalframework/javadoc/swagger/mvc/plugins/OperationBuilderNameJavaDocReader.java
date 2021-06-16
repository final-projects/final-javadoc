package org.ifinalframework.javadoc.swagger.mvc.plugins;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import org.ifinalframework.javadoc.model.ClassDoc;
import org.ifinalframework.javadoc.model.MethodDoc;

import java.lang.reflect.Field;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.RequestHandler;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * OperationBuilderNameJavaDocReader.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class OperationBuilderNameJavaDocReader implements OperationBuilderPlugin {

    /**
     * @see OperationContext#requestContext
     */
    private static final Field requestContextField = Objects.requireNonNull(ReflectionUtils
        .findField(OperationContext.class, "requestContext"));

    /**
     * @see RequestMappingContext#handler
     */
    private static final Field handlerField = Objects
        .requireNonNull(ReflectionUtils.findField(RequestMappingContext.class, "handler"));

    static {
        ReflectionUtils.makeAccessible(requestContextField);
        ReflectionUtils.makeAccessible(handlerField);
    }

    @Override
    public void apply(final OperationContext context) {
        try {

            final RequestHandler handler = (RequestHandler) ReflectionUtils
                .getField(handlerField, ReflectionUtils.getField(requestContextField, context));

            final HandlerMethod handlerMethod = handler.getHandlerMethod();
            final Class<?> beanType = handlerMethod.getBeanType();

            final ClassDoc doc = ClassDoc.load(beanType);

            if (Objects.isNull(doc)) {
                return;
            }

            final MethodDoc methodDoc = doc.getMethod(handlerMethod.getMethod());
            if (Objects.nonNull(methodDoc) && StringUtils.hasText(methodDoc.getSummary())) {
                context.operationBuilder().summary(methodDoc.getSummary());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return SwaggerPluginSupport.pluginDoesApply(documentationType);
    }

}
