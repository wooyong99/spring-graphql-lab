package com.example.graphqldemo.global.graphql.directive;

import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LogDirective implements SchemaDirectiveWiring {

    private static final Logger logger = LoggerFactory.getLogger(LogDirective.class);
    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition fieldDefinition = environment.getElement();
        GraphQLFieldsContainer fieldsContainer = environment.getFieldsContainer();

        // 기존의 DataFetcher 가져오기
        DataFetcher<?> originalDataFetcher = environment.getCodeRegistry().getDataFetcher((GraphQLObjectType) fieldsContainer, fieldDefinition);

        // 새 데이터 패처 설정
        DataFetcher<?> authDataFetcher = (DataFetchingEnvironment dataEnv) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 로그 출력 : 쿼리 정보, 호출 필드 쿼리 인자 등
            logger.info("GraphQL Query called : ");
            logger.info("Field: {}", fieldDefinition.getName());
            logger.info("Arguments: {}", dataEnv.getArguments());
            logger.info("User Info: {}", authentication.getName());      // 회원 정보가 없을 때는 anonymousUser 출력
            
            // 원래 DataFetcher 호출
            return originalDataFetcher.get(dataEnv);
        };

        // 필드에 수정된 데이터 패처를 설정하고 반환
        environment.getCodeRegistry().dataFetcher((GraphQLObjectType) fieldsContainer, fieldDefinition, authDataFetcher);
        return fieldDefinition;
    }
}
