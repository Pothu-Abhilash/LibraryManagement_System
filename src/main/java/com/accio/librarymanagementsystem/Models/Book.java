package com.accio.librarymanagementsystem.Models;

import com.accio.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String bookName;

    private Integer NoOfPages;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getNoOfPages() {
        return NoOfPages;
    }

    public void setNoOfPages(Integer noOfPages) {
        NoOfPages = noOfPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Boolean IsIssued;

    @JoinColumn
    @ManyToOne
    private Author author;

    public Boolean getIssued() {
        return IsIssued;
    }

    public void setIssued(Boolean issued) {
        IsIssued = issued;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
