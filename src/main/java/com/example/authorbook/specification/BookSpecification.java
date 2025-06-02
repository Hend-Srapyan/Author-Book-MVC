package com.example.authorbook.specification;

import com.example.authorbook.entity.Author;
import com.example.authorbook.entity.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookSpecification implements Specification<Book> {

    private BookSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + criteria.getTitle() + "%"));
        }
        if (criteria.getPrice() != null) {
            predicates.add(criteriaBuilder.equal(root.get("price"),criteria.getPrice()));
        }
        if (criteria.getQty() != null) {
            predicates.add(criteriaBuilder.equal(root.get("qty"), criteria.getQty()));
        }
        if (criteria.getAuthor() != null) {
            predicates.add(criteriaBuilder.equal(root.get("author"), criteria.getAuthor()));
        }
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
