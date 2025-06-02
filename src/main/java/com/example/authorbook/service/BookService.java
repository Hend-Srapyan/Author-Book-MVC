package com.example.authorbook.service;

import com.example.authorbook.entity.Author;
import com.example.authorbook.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    void save(Book book, MultipartFile multipartFile);

    Book findById(int id);

    void deleteById(int id);

    void update(Book book, MultipartFile multipartFile);

    List<Book> search(String keyword);
}
