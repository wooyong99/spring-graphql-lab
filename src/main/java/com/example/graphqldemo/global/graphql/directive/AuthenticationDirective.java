package com.example.graphqldemo.global.graphql.directive;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition fieldDefinition = environment.getFieldDefinition();
        GraphQLObjectType parentType = (GraphQLObjectType) environment.getFieldsContainer();

        // 기존의 DataFetcher 가져오기 
        DataFetcher<?> originalDataFetcher = environment.getCodeRegistry().getDataFetcher(parentType, fieldDefinition);
        
        // 인증/인가 작업을 수행하고 원래 DataFetcher를 호출하는 authDataFetcher 생성
        DataFetcher<?> authDataFetcher =  (DataFetchingEnvironment dataFetchingEnvironment) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getName())) {
                throw new IllegalAccessException("Access Denied - Authentication required");
            }
            // 원래 DataFetcher 호출
            return originalDataFetcher.get(dataFetchingEnvironment);
        };
        
        // 새로운 DataFetcher 등록
        environment.getCodeRegistry().dataFetcher(parentType, fieldDefinition, authDataFetcher);

        return fieldDefinition;
    }
}
