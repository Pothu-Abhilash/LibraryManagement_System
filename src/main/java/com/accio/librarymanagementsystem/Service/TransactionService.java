package com.accio.librarymanagementsystem.Service;

import com.accio.librarymanagementsystem.Enum.TranscationStatus;
import com.accio.librarymanagementsystem.Models.Book;
import com.accio.librarymanagementsystem.Models.LibraryCard;
import com.accio.librarymanagementsystem.Models.Transaction;
import com.accio.librarymanagementsystem.Repositories.BookRepository;
import com.accio.librarymanagementsystem.Repositories.CardRepository;
import com.accio.librarymanagementsystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String issueBook(int cardId,int bookId) throws Exception {

        Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isEmpty()){
            throw new Exception("Invalid cardId");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new Exception("Invalid bookId");
        }

        Book book = optionalBook.get();
        LibraryCard card = optionalCard.get();

        Transaction transaction = new Transaction();




        if(book.getIsIssued() == true){
            transaction.setTranscationStatus(TranscationStatus.FAILURE);
            throw new Exception("book is already issued");
        }
        if(card.getNoOfBooksIssued()==3){
            throw new Exception("Card Book issue Limit is reached");
        }



        transaction.setTranscationStatus(TranscationStatus.SUCCESS);
        transaction.setCard(card);
        transaction.setReturnDate(transaction.getIssueDate().plusWeeks(1));
        transaction.setBook(book);
        book.setIsIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        transaction = transactionRepository.save(transaction);

        cardRepository.save(card);
        bookRepository.save(book);


        return "The transaction is saved with transactionId : "+transaction.getTransactionId();
    }


    public String returnBook(Integer cardId, Integer bookId) throws Exception {

        Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isEmpty()){
            throw new Exception("Invalid cardId");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new Exception("Invalid bookId");
        }

        Book book = optionalBook.get();
        LibraryCard card = optionalCard.get();

        Transaction transaction = new Transaction();
        if(book.getIsIssued() == false){
            transaction.setTranscationStatus(TranscationStatus.FAILURE);
            throw new Exception(("This book is not been issued"));
        }
        if(card.getNoOfBooksIssued() == 0){
            transaction.setTranscationStatus(TranscationStatus.FAILURE);
            throw new Exception(("No Books takens by this card"));
        }


        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = transaction.getReturnDate();
        int fineAmount = 0;
        if(currentDate.isAfter(returnDate)){
            long days = ChronoUnit.DAYS.between(currentDate, returnDate);
            fineAmount = (int) days * 5;
        }
        transaction.setFineAmt(fineAmount);
        transaction.setTranscationStatus(TranscationStatus.SUCCESS);
        book.setIsIssued(false);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        transaction = transactionRepository.save(transaction);

        cardRepository.save(card);
        bookRepository.save(book);

        return "The transaction is saved with transactionId : "+transaction.getTransactionId();

    }
}