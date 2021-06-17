package org.ifinalframework.javadoc.swagger;

import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

/**
 * SwaggerUtils.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public final class SwaggerUtils {

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

    private static final Field PARAMETER_NAME = Objects
        .requireNonNull(ReflectionUtils.findField(RequestParameterBuilder.class, "name"));

    static {
        Stream.of(
            requestContextField, handlerField, PARAMETER_NAME
        ).forEach(ReflectionUtils::makeAccessible);
    }

    private SwaggerUtils() {
    }

    public static HandlerMethod findHandlerMethod(OperationContext context) {
        final RequestHandler handler = (RequestHandler) ReflectionUtils
            .getField(handlerField, ReflectionUtils.getField(requestContextField, context));

        return handler.getHandlerMethod();

    }

}
