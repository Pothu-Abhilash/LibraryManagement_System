package com.accio.librarymanagementsystem.Service;

import com.accio.librarymanagementsystem.Models.Author;
import com.accio.librarymanagementsystem.Models.Book;
import com.accio.librarymanagementsystem.Repositories.AuthorRepository;
import com.accio.librarymanagementsystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book){

        bookRepository.save(book);

        return "Book has been saved to DB with bookId "+book.getBookId();
    }

    public Book getBook(Integer bookId) throws Exception{

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()){
            throw new Exception("Entered Invalid BookId");
        }

        Book book = optionalBook.get();
        return book;
    }

    public String associateBookAndAuthor(Integer bookId, Integer authorId) throws Exception{

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new Exception("Entered bookId is invalid");
        }

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty()) {
            throw new Exception("Entered AuthorId is invalid");
        }

        Book book = optionalBook.get();
        Author author = optionalAuthor.get();

        book.setAuthor(author);
        author.setNoOfBooks(author.getNoOfBooks()+1);

        bookRepository.save(book);
        authorRepository.save(author);

        return "Book and author have been associated";
    }
}

