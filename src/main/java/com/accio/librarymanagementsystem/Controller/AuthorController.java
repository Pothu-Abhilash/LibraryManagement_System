package com.accio.librarymanagementsystem.Controller;

import com.accio.librarymanagementsystem.Models.Author;
import com.accio.librarymanagementsystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody Author author){

        String result = authorService.addAuthor(author);

        return result;
    }

    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam("authorId")Integer authorId) {

        try {
            Author response = authorService.getAuthor(authorId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAllAuthors")
    public List<String> findAllAuthors(){
        List<String> authors = authorService.findAllAuthors();
        return authors;
    }

    @GetMapping("/maxBooksWrittenByAuthor")
    public ResponseEntity maxBooksWrittenByAuthor(){

        try {
            String response = authorService.maxBooksWrittenByAuthor();
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
