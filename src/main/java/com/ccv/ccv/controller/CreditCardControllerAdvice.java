package com.ccv.ccv.controller;

import com.ccv.ccv.model.CreditCardSubmissionException;
import com.ccv.ccv.model.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CreditCardControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RecordNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retrieve(RecordNotFoundException recordNotFoundException) {
        return recordNotFoundException.printMessage();
    }

    @ExceptionHandler({ CreditCardSubmissionException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String submit(CreditCardSubmissionException creditCardSubmissionException) {
        return creditCardSubmissionException.printMessage();
    }

}
