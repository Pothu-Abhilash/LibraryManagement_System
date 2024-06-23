package com.accio.librarymanagementsystem.Service;

import com.accio.librarymanagementsystem.Controller.AuthorController;
import com.accio.librarymanagementsystem.Models.Author;
import com.accio.librarymanagementsystem.Repositories.AuthorRepository;
import com.accio.librarymanagementsystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    public String addAuthor(Author author){

        authorRepository.save(author);

        return "Author has been added to DB with the Author Id"+author.getAuthorId();
    }

    public Author getAuthor(Integer authorId) throws Exception{

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty()){
            throw new Exception("Entered Invalid AuthorId");
        }

        Author author = optionalAuthor.get();
        return author;
    }

    public List<String> findAllAuthors(){
       List<Author> authorList = authorRepository.findAll();
       List<String> authorNames = new ArrayList<>();

       for(Author author : authorList){
           authorNames.add(author.getAuthorName());
       }
       return authorNames;
    }

    public String maxBooksWrittenByAuthor() {
        List<String>authorsList = findAllAuthors();

        int maxNoOfBooks = 0;
        String authorName = "";
        for(String authors : authorsList){
            List<String> bookList = bookService.findAllBooksOfAuthor(authors);
//            System.out.println(authors);
            if(bookList.size() > maxNoOfBooks){
                maxNoOfBooks = bookList.size();
                authorName = authors;
                System.out.println(authorName);
            }
        }
        return authorName;
    }
}
