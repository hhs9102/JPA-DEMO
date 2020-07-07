package me.ham.support;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public abstract class QuerydslCustomRepositorySupport extends QuerydslRepositorySupport {
    protected JPAQueryFactory queryFactory;

    public QuerydslCustomRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    protected <T> JPQLQuery<T> selectFrom(EntityPath<T> path) {
        return queryFactory.from(path).select(path);
    }
}
