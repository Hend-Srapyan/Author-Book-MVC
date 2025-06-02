package com.example.authorbook.service.impl;

import com.example.authorbook.entity.Author;
import com.example.authorbook.entity.Book;
import com.example.authorbook.repository.BookRepository;
import com.example.authorbook.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Value("${author.book.upload.path}")
    private String uploadPath;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @SneakyThrows
    @Override
    public void save(Book book, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            book.setImageName(fileName);
        }
        book.setCreatedAt(new Date());
        bookRepository.save(book);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public void update(Book book, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            book.setImageName(fileName);
        }
        book.setCreatedAt(new Date());
        bookRepository.save(book);
    }

    @Override
    public List<Book> search(String keyword) {
        return bookRepository.findAllByTitleContaining(keyword);
    }
}
