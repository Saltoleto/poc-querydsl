package br.com.saltoleto.repository;

import br.com.saltoleto.util.DateUtil;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.math.BigDecimal;
import java.util.*;

public interface SaltoletoQuerydslBinderCustomizer<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {


    @Override
    default void customize(QuerydslBindings querydslBindings, T entityPath) {
        querydslBindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));

        querydslBindings.bind(Float.class).all(
                (final NumberPath<Float> path, final Collection<? extends Float> values) -> getNumberCondition(path, values));

        querydslBindings.bind(Integer.class).all(
                (final NumberPath<Integer> path, final Collection<? extends Integer> values) -> getNumberCondition(path, values));

        querydslBindings.bind(BigDecimal.class).all(
                (final NumberPath<BigDecimal> path, final Collection<? extends BigDecimal> values) -> getNumberCondition(path, values));

        querydslBindings.bind(Long.class).all(
                (final NumberPath<Long> path, final Collection<? extends Long> values) -> getNumberCondition(path, values));

        querydslBindings.bind(Short.class).all(
                (final NumberPath<Short> path, final Collection<? extends Short> values) -> getNumberCondition(path, values));

        querydslBindings.bind(Date.class).all(
                (final Path<Date> path, final Collection<? extends Date> values) -> getDateCondition(path, values));
    }

    @SuppressWarnings({"rawtypes"})
    default Optional getDateCondition(final Path path, Collection<? extends Date> values) {
        if (values.size() == 2) {
            final List<? extends Date> dateValues = new ArrayList<>(values);
            return criarExpressaoBetween(dateValues.get(0), dateValues.get(1), path);
        }
        Date data = values.iterator().next();
        return criarExpressaoBetween(data, data, path);
    }

    @SuppressWarnings("unchecked")
    static Optional criarExpressaoBetween(Date dataInicial, Date dataFinal, Path path) {
        dataInicial = DateUtil.setHoursMinutesSeconds(dataInicial, 0, 0, 0);
        dataFinal = DateUtil.setHoursMinutesSeconds(dataFinal, 23, 59, 59);
        return Optional.of(((ComparableExpression<Date>) path).between(dataInicial, dataFinal));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default Optional getNumberCondition(final NumberPath path, Collection<? extends Number> values) {
        final BooleanExpression expression;

        if (values.size() == 2) {
            final List<? extends Comparable> numberValues = new ArrayList(values);
            Collections.sort(numberValues);
            expression = path.between((Number) numberValues.get(0), (Number) numberValues.get(1));
        } else {
            expression = path.eq(values.iterator().next());
        }

        return Optional.of(expression);
    }

}
