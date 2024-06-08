package com.accio.librarymanagementsystem.Controller;

import com.accio.librarymanagementsystem.Models.LibraryCard;
import com.accio.librarymanagementsystem.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Card")
public class LibraryCardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/addCard")
    public String addCard(){

        return cardService.addCard();
    }

    @GetMapping("/getCard")
    public ResponseEntity getCard(@RequestParam("cardId") Integer cardId)  {

        try {
            LibraryCard card = cardService.getCard(cardId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/associateCardAndStudent")
    public String associateCardAndStudent(@RequestParam("cardId")Integer cardId,
                                          @RequestParam("studentId")Integer studentId){

        try{
            return cardService.associateCardAndStudent(cardId,studentId);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
