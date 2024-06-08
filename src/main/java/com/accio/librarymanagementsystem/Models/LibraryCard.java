package com.accio.librarymanagementsystem.Models;

import com.accio.librarymanagementsystem.Enum.CardStatus;
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
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    private Integer noOfBooksIssued;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    //this has to be written in child class
    @JoinColumn //by default it adds the PK, incase you add any other column
    @OneToOne //Mapping between entries
    private Student student; //Which entity you have connected

}
