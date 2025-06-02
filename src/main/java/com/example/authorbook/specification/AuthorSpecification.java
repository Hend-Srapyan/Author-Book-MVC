package com.example.authorbook.specification;

import com.example.authorbook.entity.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AuthorSpecification implements Specification<Author> {

    private AuthorSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
               predicates.add(criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%"));
            }
            if (criteria.getSurname() != null && !criteria.getSurname().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("surname"), "%" + criteria.getSurname() + "%"));
            }
            if (criteria.getPhone() != null && !criteria.getPhone().isEmpty()) {
               predicates.add(criteriaBuilder.like(root.get("phone"), "%" + criteria.getPhone() + "%"));
            }
            if (criteria.getGender() != null) {
              predicates.add(criteriaBuilder.equal(root.get("gender"), criteria.getGender().name()));
            }
            return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
