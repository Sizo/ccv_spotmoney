package com.ccv.ccv.service;

import com.ccv.ccv.model.*;
import com.ccv.ccv.repository.CreditCardRepository;
import com.ccv.ccv.service.client.Binlist;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CreditCardServiceTest {

    public static final String EXPECTED_CREDIT_CARD_NUMBER1 = "1234567890";
    public static final String EXPECTED_CREDIT_CARD_NUMBER2 = "0123456789";
    @InjectMocks
    private CreditCardService creditCardService;

    @Mock
    CreditCardRepository creditCardRepository;

    @Mock
    private Binlist binlist;

    @Mock
    private List<String> bannedCountries = new ArrayList<>();

    @BeforeEach
    void setUp() {
        bannedCountries.add("Denmark");
        List<CreditCardNumber> mockList = new ArrayList<>();
        mockList.add(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER1));
        mockList.add(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER2));
        when(creditCardRepository.findAll())
                .thenReturn(mockList);

        CreditCardInfo creditCardInfo = CreditCardInfo.builder()
                .country(Country.builder().name("South Africa").build())
                .build();
        when(binlist.getCreditCardDetails(any()))
                .thenReturn(creditCardInfo);

        when(creditCardRepository.findById(EXPECTED_CREDIT_CARD_NUMBER1))
                .thenReturn(Optional.of(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER1)));
    }

    @Test
    @DisplayName("Submission database error test.")
    void submitCreditCardNumberDatabaseError() {
        when(creditCardRepository.save(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER2)))
                .thenThrow(new JpaObjectRetrievalFailureException(new EntityNotFoundException()));
        assertThrows(CreditCardSubmissionException.class,
                () -> creditCardService.submitCreditCardNumber(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER2)));
    }

    @Test
    @DisplayName("Credit card submit test.")
    void submitCreditCardNumber() {
        assertDoesNotThrow(()->creditCardService.submitCreditCardNumber(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER1)));
    }

    @Test
    @DisplayName("Credit card from banned country submit test.")
    void submitCreditCardNumberBannedCountry() {
        when(bannedCountries.contains(any())).thenReturn(true);
        assertThrows(BannedCountryException.class,
                () -> creditCardService.submitCreditCardNumber(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER1)));
    }


    @Test
    @DisplayName("Get All credit card numbers test.")
    void retrieveAll() {
        List<CreditCardNumber> expectedList = new ArrayList<>();
        expectedList.add(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER1));
        expectedList.add(new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER2));
        var actualList =  creditCardService.retrieveAll();
        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Get specified credit card number test.")
    void retrieve() {
        CreditCardNumber nonExistentCreditCardNumber = new CreditCardNumber("54321");
        CreditCardNumber expectedNumber = new CreditCardNumber(EXPECTED_CREDIT_CARD_NUMBER1);
        assertEquals(expectedNumber, creditCardService.retrieve(expectedNumber));
        assertThrows(RecordNotFoundException.class, ()->creditCardService.retrieve(nonExistentCreditCardNumber));
    }
}