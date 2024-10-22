package com.example.graphqldemo.global.config;

import com.example.graphqldemo.global.directive.AuthenticationDirective;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(AuthenticationDirective authenticationDirective){
        return wiringBuilder ->
                wiringBuilder
                        .scalar(ExtendedScalars.GraphQLLong)
                        .scalar(ExtendedScalars.Date)
                        .scalar(ExtendedScalars.DateTime)
                        .directive("auth", authenticationDirective);
    }

}
