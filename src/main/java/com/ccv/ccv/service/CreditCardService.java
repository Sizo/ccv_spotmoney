package com.ccv.ccv.service;

import com.ccv.ccv.model.CreditCardNumber;
import com.ccv.ccv.model.CreditCardSubmissionException;
import com.ccv.ccv.model.RecordNotFoundException;
import com.ccv.ccv.repository.CreditCardRepository;
import com.ccv.ccv.service.client.Binlist;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    private Binlist binlist;

    @Value("#{'${country.banned}'.split(',')}")
    private List<String> myList;

    public void submitCreditCardNumber(final CreditCardNumber number){
        final var creditCardNumber = number.getCreditCardNumber();
        try {
            log.info("Saving {}.", creditCardNumber);
            var response = binlist.getCreditCardDetails(creditCardNumber);
            var country = response.getCountry().getName();
            log.info(response.toString());


            creditCardRepository.save(number);
            log.info("Saved {}.", creditCardNumber);
        }catch (DataAccessException ex){
            log.error("An error occurred submitting record {}.", creditCardNumber);
            throw new CreditCardSubmissionException(creditCardNumber);
        }
    }

    public List<CreditCardNumber> retrieveAll(){
        log.info("Fetching all records");
        return creditCardRepository.findAll();
    }

    public CreditCardNumber retrieve(final CreditCardNumber creditCardNumber){
        final var ccn = creditCardNumber.getCreditCardNumber();
        log.info("Searching for {}.", ccn);
        return creditCardRepository.findById(ccn).orElseThrow(()->{
            log.error("Record {} not found.", ccn);
            return new RecordNotFoundException(ccn);
        });
    }
}
