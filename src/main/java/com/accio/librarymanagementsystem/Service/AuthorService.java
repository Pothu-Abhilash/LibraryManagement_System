package com.accio.librarymanagementsystem.Service;

import com.accio.librarymanagementsystem.Controller.AuthorController;
import com.accio.librarymanagementsystem.Models.Author;
import com.accio.librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

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
}
