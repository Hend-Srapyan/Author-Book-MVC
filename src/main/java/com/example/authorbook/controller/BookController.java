package com.example.authorbook.controller;

import com.example.authorbook.entity.Author;
import com.example.authorbook.entity.Book;
import com.example.authorbook.service.AuthorService;
import com.example.authorbook.service.BookService;
import com.example.authorbook.specification.AuthorSearchCriteria;
import com.example.authorbook.specification.BookSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public String booksPage(ModelMap modelMap) {
        List<Book> all = bookService.findAll();
        modelMap.put("books", all);
        return "book/books";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam("keyword") String keyword, ModelMap modelMap) {
        List<Book> searchResult = bookService.search(keyword);
        modelMap.addAttribute("books", searchResult);
        return "book/booksSearch";
    }

    @GetMapping("/filter")
    public String filterBook(@ModelAttribute BookSearchCriteria criteria, ModelMap modelMap) {
        modelMap.put("authors", authorService.findAll());
        List<Book> searchResult = bookService.filter(criteria);
        modelMap.addAttribute("books", searchResult);
        modelMap.addAttribute("title", criteria.getTitle());
        modelMap.addAttribute("price", criteria.getPrice());
        modelMap.addAttribute("qty", criteria.getQty());
        modelMap.addAttribute("author", criteria.getAuthor());

        return "book/booksSearch";
    }

    @GetMapping("/add")
    public String addBookPage(ModelMap modelMap) {
        List<Author> all = authorService.findAll();
        modelMap.put("authors", all);
        return "book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book,
                          @RequestParam("image")MultipartFile multipartFile) {
        bookService.save(book, multipartFile);
        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") int id, ModelMap modelMap) {
        Book book = bookService.findById(id);
        List<Author> all = authorService.findAll();
        modelMap.put("authors", all);
        if (book != null) {
            modelMap.put("book", book);
            return "book/editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book,
                           @RequestParam("image")MultipartFile multipartFile){

        bookService.update(book, multipartFile);
        return "redirect:/books";
    }
}
