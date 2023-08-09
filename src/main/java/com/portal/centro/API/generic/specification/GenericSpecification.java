package com.portal.centro.API.generic.specification;

import com.portal.centro.API.specification.SpecSearchCriteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class GenericSpecification<T> implements Specification<T> {

    private SpecSearchCriteria criteria;

    public GenericSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                if (criteria.getValue().toString().equals("true") || criteria.getValue().toString().equals("false")) {
                    return builder.equal(root.get(criteria.getKey()), Boolean.parseBoolean(criteria.getValue().toString()));
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case DATE:
                if (criteria.getValue().toString().length() == 8) {
                    return builder.between(root.get(criteria.getKey()),
                            LocalDateTime.of(
                                    Integer.parseInt(criteria.getValue().toString().substring(4,8)),
                                    Integer.parseInt(criteria.getValue().toString().substring(2,4)),
                                    Integer.parseInt(criteria.getValue().toString().substring(0,2)),00,00,00),
                            LocalDateTime.of(
                                    Integer.parseInt(criteria.getValue().toString().substring(4,8)),
                                    Integer.parseInt(criteria.getValue().toString().substring(2,4)),
                                    Integer.parseInt(criteria.getValue().toString().substring(0,2)),23,59,59));
                }else {
                    return builder.between(root.get(criteria.getKey()),
                            LocalDateTime.of(
                                    Integer.parseInt(criteria.getValue().toString().substring(4,8)),
                                    Integer.parseInt(criteria.getValue().toString().substring(2,4)),
                                    Integer.parseInt(criteria.getValue().toString().substring(0,2)),00,00,00),
                            LocalDateTime.of(
                                    Integer.parseInt(criteria.getValue().toString().substring(12,16)),
                                    Integer.parseInt(criteria.getValue().toString().substring(10,12)),
                                    Integer.parseInt(criteria.getValue().toString().substring(8,10)),23,59,59));
                }
            default:
                return null;
        }
    }

}

