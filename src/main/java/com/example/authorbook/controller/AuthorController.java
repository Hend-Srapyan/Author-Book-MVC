package com.example.authorbook.controller;

import com.example.authorbook.entity.Author;
import com.example.authorbook.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;


    @GetMapping
    public String authors(ModelMap modelMap) {
        List<Author> all = authorService.findAll();
        modelMap.addAttribute("authors", all);
        return "author/authors";
    }

    @GetMapping("/add")
    public String addAuthorPage() {
        return "author/addAuthor";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author,
                            @RequestParam("image")MultipartFile multipartFile){
        authorService.save(author, multipartFile);
        return "redirect:/authors";
    }

    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("id") int id){
        authorService.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping("/edit")
    public String editAuthorPage(@RequestParam("id") int id, ModelMap modelMap) {
        Author author = authorService.findById(id);
        if (author != null) {
            modelMap.put("author", author);
            return "author/editAuthor";
        }
        return "redirect:/authors";
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute Author author,
                             @RequestParam("image")MultipartFile multipartFile){
        authorService.update(author, multipartFile);
        return "redirect:/authors";
    }
}
