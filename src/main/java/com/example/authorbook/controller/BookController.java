package com.example.authorbook.controller;

import com.example.authorbook.entity.Author;
import com.example.authorbook.entity.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String booksPage(ModelMap modelMap) {
        List<Book> all = bookRepository.findAll();
        modelMap.put("books", all);
        return "book/books";
    }

    @GetMapping("/add")
    public String addBookPage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.put("authors", all);
        return "book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        book.setCreatedAt(new Date());
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Book> byId = bookRepository.findById(id);
        List<Author> all = authorRepository.findAll();
        modelMap.put("authors", all);
        if (byId.isPresent()) {
            Book book = byId.get();
            modelMap.put("book", book);
            return "book/editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book){
        book.setCreatedAt((new Date()));
        bookRepository.save(book);
        return "redirect:/books";
    }
}
