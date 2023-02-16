package com.ccv.ccv.controller;

import com.ccv.ccv.model.CreditCardNumber;
import com.ccv.ccv.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "credit/")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping(value = "submit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void submitCreditCardNumber(@RequestBody @NotNull final CreditCardNumber creditCardNumber) {
        creditCardService.submitCreditCardNumber(creditCardNumber);
    }

    @GetMapping(value = "retrieveAll")
    public ResponseEntity<List<CreditCardNumber>> retrieveAll(){
        return new ResponseEntity<>(creditCardService.retrieveAll(), HttpStatus.OK);
    }

    @PostMapping(value = "retrieve")
    public ResponseEntity<CreditCardNumber> retrieve(@RequestBody @NotEmpty CreditCardNumber creditCardNumber){
        return new ResponseEntity<>(creditCardService.retrieve(creditCardNumber), HttpStatus.FOUND);
    }
}
