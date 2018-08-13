package ru.shop.core.service.dto.filter;

import org.springframework.data.jpa.domain.Specification;
import ru.shop.core.persistense.entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {
    private BookFilterDTO bookFilterDTO;

    public BookSpecification(BookFilterDTO bookFilterDTO) {
        this.bookFilterDTO = bookFilterDTO;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (bookFilterDTO.getTitle() != null && !bookFilterDTO.getTitle().isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + bookFilterDTO.getTitle().trim().toLowerCase() + "%"));
        }

        if (bookFilterDTO.getPubyear() != null){
            predicates.add(criteriaBuilder.equal(root.get("pubyear"), bookFilterDTO.getPubyear()));
        }

        if (bookFilterDTO.getAuthor() != null && !bookFilterDTO.getAuthor().isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("authors").get("fio")), "%" + bookFilterDTO.getAuthor().trim().toLowerCase() + "%"));
        }

        if (bookFilterDTO.getGenre() != null && !bookFilterDTO.getGenre().isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("genres").get("name")), "%" + bookFilterDTO.getGenre().trim().toLowerCase() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
