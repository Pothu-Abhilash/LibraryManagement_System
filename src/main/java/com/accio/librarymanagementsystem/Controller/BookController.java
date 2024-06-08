package com.accio.librarymanagementsystem.Controller;

import com.accio.librarymanagementsystem.Models.Book;
import com.accio.librarymanagementsystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){

        return bookService.addBook(book);
    }

    @GetMapping("/getBook")
    public ResponseEntity getBook(@RequestParam("bookId") Integer bookId)  {

        try {
            Book book = bookService.getBook(bookId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/associateBookAndAuthor")
    public ResponseEntity associateBookAndAuthor(@RequestParam("bookId")Integer bookId,
                                                 @RequestParam("authorId")Integer authorId){
        try {
            String response = bookService.associateBookAndAuthor(bookId,authorId);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
