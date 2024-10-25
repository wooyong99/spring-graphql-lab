package com.example.graphqldemo.global.graphql.config;

import com.example.graphqldemo.domain.version.entity.Version;
import com.example.graphqldemo.global.graphql.directive.AuthenticationDirective;
import com.example.graphqldemo.global.graphql.directive.LogDirective;
import graphql.scalars.ExtendedScalars;
import graphql.schema.DataFetcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Date;
import java.util.Map;

@Configuration
public class GraphQLConfig {

//    GraphQL Java의 RuntimeWiringConfigurer는 DataFetchers, TypeResolver, Custom ScalarType 등을 등록하는데 사용된다.
//    실질적인 로직은 RuntimeWiringConfigurer 에 daaFetcher를 등록함으로써 graphQL API 호출에 대해 로직이 동작한다.
//    DateFetcher는 GraphQL Java에서 가장 중요한 개념 중 하나이다. GraphQL API 요청을 받으면 쿼리가 실행되는 동안 쿼리에서 요청한 각 필드들에 대해 적절한 DateFetcher가 호출된다.
//    스키마의 모든 필드에는 연결된 DataFetcher가 존재하는데 특정 필드에 대해 DataFetcher를 지정하지 않으면 기본 값으로 PropertyDataFetcher가 사용된다.
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(
            AuthenticationDirective authenticationDirective,
            LogDirective logDirective){
        return wiringBuilder ->
                wiringBuilder
                        .scalar(ExtendedScalars.GraphQLLong)
                        .scalar(ExtendedScalars.Date)
                        .scalar(ExtendedScalars.DateTime)
                        .type("Query", builder -> builder.dataFetchers(dataFetcherMap))
                        .directive("auth", authenticationDirective)
                        .directive("log", logDirective);
    }

    private Map<String, DataFetcher> dataFetcherMap = Map.of(
            "version", env -> new Version("1.1.1", "version", new Date()),
            "versions", env -> new Version("1.1.2", "versions", new Date())
    );

}
