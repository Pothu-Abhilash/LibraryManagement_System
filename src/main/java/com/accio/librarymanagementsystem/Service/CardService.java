package com.accio.librarymanagementsystem.Service;

import com.accio.librarymanagementsystem.Enum.CardStatus;
import com.accio.librarymanagementsystem.Models.LibraryCard;
import com.accio.librarymanagementsystem.Models.Student;
import com.accio.librarymanagementsystem.Repositories.CardRepository;
import com.accio.librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;
    public String addCard(){

        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.New);
        card.setNoOfBooksIssued(0);
        cardRepository.save(card);
        return "Card has been added to DB with cardId "+card.getCardId();
    }

    public LibraryCard getCard(Integer cardId) throws Exception{

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalLibraryCard.isEmpty())
        {
            throw new Exception("Entered Invalid CardId");
        }

        LibraryCard card = optionalLibraryCard.get();
        return card;
    }

    public String associateCardAndStudent(Integer cardId, Integer studentId) throws Exception{

        LibraryCard card = cardRepository.findById(cardId).get();
        Student student = studentRepository.findById(studentId).get();

        if(card.getCardStatus().equals(CardStatus.Active))
        {
            throw new Exception("This card id already associated with student");
        }

        List<LibraryCard> cardList = cardRepository.findAll();
        for(LibraryCard card1 : cardList)
        {
            if(card1.getCardStatus().equals(CardStatus.Active) && card1.getStudent().getStudentId() == studentId){
                throw new Exception("This studentId already associated with card");
            }
        }
        card.setStudent(student);
        card.setCardStatus(CardStatus.Active);

        cardRepository.save(card);

        return "Associated card and student with cardId "+card.getCardId()+" studentId "+student.getStudentId();
    }
}
