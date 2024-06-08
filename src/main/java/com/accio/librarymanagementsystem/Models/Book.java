package com.accio.librarymanagementsystem.Models;

import com.accio.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String bookName;

    private Integer NoOfPages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

//    private Boolean isIssued;

    @JoinColumn
    @OneToOne
    private Author author;
}
