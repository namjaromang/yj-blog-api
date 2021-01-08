package com.blog.api.utility;

import com.blog.api.exception.GraphQLErrorAdapter;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.GraphQLObjectMapper;
import graphql.kickstart.execution.config.GraphQLServletObjectMapperConfigurer;
import graphql.kickstart.execution.config.ObjectMapperProvider;
import graphql.kickstart.execution.error.DefaultGraphQLErrorHandler;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import graphql.kickstart.spring.error.ErrorHandlerSupplier;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;

@Configuration
public class GraphQLErrorHandlerConfig {
    public static final Logger log = LoggerFactory.getLogger(DefaultGraphQLErrorHandler.class);

    @Autowired(required = false)
    GraphQLServletObjectMapperConfigurer objectMapperConfigurer;

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = this.filterGraphQLErrors(errors);
                if (clientErrors.size() < errors.size()) {
                    clientErrors.add(
                            new GenericGraphQLError(
                                    "Internal Server Error(s) while executing query"));
                    errors.stream()
                            .filter(
                                    (error) -> {
                                        return !this.isClientError(error);
                                    })
                            .forEach(
                                    (error) -> {
                                        if (error instanceof Throwable) {
                                            log.error("Error executing query!", (Throwable) error);
                                        } else {
                                            log.error(
                                                    "Error executing query ({}): {}",
                                                    error.getClass().getSimpleName(),
                                                    error.getMessage());
                                        }
                                    });
                }
                return clientErrors;
            }

            private List filterGraphQLErrors(List<GraphQLError> errors) {
                return errors.stream()
                        .filter(this::isClientError)
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());
            }

            private boolean isClientError(GraphQLError error) {
                if (error instanceof ExceptionWhileDataFetching) {
                    Throwable e = ((ExceptionWhileDataFetching) error).getException();
                    return e instanceof GraphQLError || e instanceof AccessDeniedException;
                } else {
                    return !(error instanceof Throwable);
                }
            }
        };
    }

    /**
     * 에러 핸들링에 대한 특이사항 GraphQL 라이브러리가 6.x -> 7.x 로 가며 에러핸들링이 의도된대로 동작되지 않습니다 해결하려면 아래
     * GraphQLObjectMapper Bean을 재정의 해줄 필요가 있습니다
     *
     * <p>출처 : https://github.com/graphql-java-kickstart/graphql-spring-boot/issues/379
     *
     * @param objectMapperProviderObjectProvider
     * @param graphQLExceptionHandler
     * @return
     */
    @Bean
    public GraphQLObjectMapper graphQLObjectMapper(
            ObjectProvider<ObjectMapperProvider> objectMapperProviderObjectProvider,
            GraphQLErrorHandler graphQLExceptionHandler) {
        GraphQLObjectMapper.Builder builder = new GraphQLObjectMapper.Builder();
        builder.withGraphQLErrorHandler(new ErrorHandlerSupplier(graphQLExceptionHandler));
        ObjectMapperProvider objectMapperProvider =
                objectMapperProviderObjectProvider.getIfAvailable();
        if (objectMapperProvider != null) {
            builder.withObjectMapperProvider(objectMapperProvider);
        } else if (objectMapperConfigurer != null) {
            builder.withObjectMapperConfigurer(objectMapperConfigurer);
        }
        return builder.build();
    }
}
