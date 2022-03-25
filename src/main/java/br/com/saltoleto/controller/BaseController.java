package br.com.saltoleto.controller;


import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public abstract class BaseController<T> {

    private final QuerydslPredicateExecutor<T> executor;

    public BaseController(QuerydslPredicateExecutor<T> executor) {
        this.executor = executor;
    }

    protected Page getCollectionResource(Predicate predicate, Pageable pageable) {

        return executor.findAll(predicate, pageable);
    }


}
