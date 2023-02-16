package com.ccv.ccv.controller;

import com.ccv.ccv.model.BannedCountryException;
import com.ccv.ccv.model.CreditCardSubmissionException;
import com.ccv.ccv.model.RecordNotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

@RestControllerAdvice
public class CreditCardControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RecordNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retrieve(@NotNull RecordNotFoundException recordNotFoundException) {
        return recordNotFoundException.printMessage();
    }

    @ExceptionHandler({ CreditCardSubmissionException.class, FeignException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String submit(@NotNull CreditCardSubmissionException creditCardSubmissionException) {
        return creditCardSubmissionException.printMessage();
    }

    @ExceptionHandler({ BannedCountryException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String submit(@NotNull BannedCountryException bannedCountryException) {
        return bannedCountryException.printMessage();
    }

}
