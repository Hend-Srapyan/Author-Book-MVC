package com.example.authorbook.controller;


import com.example.authorbook.entity.Author;
import com.example.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public String authors(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "authors";
    }

    @GetMapping("/authors/add")
    public String addAuthorPage() {
        return "addAuthor";
    }
}
