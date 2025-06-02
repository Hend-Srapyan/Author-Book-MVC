package com.example.authorbook.service.impl;

import com.example.authorbook.entity.Author;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.service.AuthorService;
import com.example.authorbook.specification.AuthorSpecification;
import com.example.authorbook.specification.AuthorSearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    @Value("${author.book.upload.path}")
    private String uploadPath;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @SneakyThrows
    @Override
    public void save(Author author, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            author.setImageName(fileName);
        }
        authorRepository.save(author);
    }

    @Override
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @SneakyThrows
    @Override
    public void update(Author author, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            author.setImageName(fileName);
        }
        authorRepository.save(author);
    }

    @Override
    public List<Author> search(String keyword) {
        return authorRepository.findAllByNameContainingOrSurnameContaining(keyword, keyword);
    }

    @Override
    public List<Author> filter(AuthorSearchCriteria searchCriteria) {
        AuthorSpecification authorSpecification = new AuthorSpecification(searchCriteria);
        List<Author> all = authorRepository.findAll(authorSpecification);
        return all;
    }
}
