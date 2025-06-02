package com.example.authorbook.service;


import com.example.authorbook.entity.Author;
import com.example.authorbook.specification.AuthorSearchCriteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    void save(Author author, MultipartFile multipartFile);

    void deleteById(int id);

    Author findById(int id);

    void update(Author author, MultipartFile multipartFile);

    List<Author> search(String keyword);

    List<Author> filter(AuthorSearchCriteria searchCriteria);
}
