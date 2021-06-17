package org.ifinalframework.javadoc.swagger.mvc.plugins;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import org.ifinalframework.javadoc.model.ClassDoc;
import org.ifinalframework.javadoc.model.MethodDoc;
import org.ifinalframework.javadoc.model.ParameterDoc;
import org.ifinalframework.javadoc.swagger.SwaggerUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import springfox.documentation.builders.RequestParameterBuilder;
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

    private static final Field PARAMETER_NAME = Objects
        .requireNonNull(ReflectionUtils.findField(RequestParameterBuilder.class, "name"));

    static {
        ReflectionUtils.makeAccessible(PARAMETER_NAME);
    }

    @Override
    public void apply(final ParameterContext parameterContext) {

        final String name = (String) ReflectionUtils
            .getField(PARAMETER_NAME, parameterContext.requestParameterBuilder());

        final HandlerMethod handlerMethod = SwaggerUtils.findHandlerMethod(parameterContext.getOperationContext());

        final Class<?> type = handlerMethod.getBeanType();

        final ClassDoc doc = ClassDoc.load(type);

        if (Objects.isNull(doc)) {
            return;
        }

        final MethodDoc methodDoc = doc.getMethod(handlerMethod.getMethod());
        if (Objects.isNull(methodDoc)) {
            return;
        }

        final Map<String, ParameterDoc> parameterDocs = methodDoc.getParameterDocs().stream()
            .collect(Collectors.toMap(ParameterDoc::getName, Function.identity()));

        final ParameterDoc parameterDoc = parameterDocs.get(name);
        if (Objects.nonNull(parameterDoc) && StringUtils.hasText(parameterDoc.getSummary())) {
            parameterContext.requestParameterBuilder().description(parameterDoc.getSummary());
        }

    }

    @Override
    public boolean supports(final DocumentationType documentationType) {
        return true;
    }

}
