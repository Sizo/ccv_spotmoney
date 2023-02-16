package com.ccv.ccv.controller;


import com.ccv.ccv.model.Country;
import com.ccv.ccv.model.CreditCardInfo;
import com.ccv.ccv.model.CreditCardNumber;
import com.ccv.ccv.repository.CreditCardRepository;
import com.ccv.ccv.service.CreditCardService;
import com.ccv.ccv.service.client.Binlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class CreditCardControllerTest {

    public static final String CREDIT_CARD_NUMBER = "4023609876543221";
    ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private CreditCardService creditCardService;

    @MockBean
    private Binlist binlist;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @Test
    @DisplayName("Submit credit card mvc test")
    void submitCreditCardNumber() throws Exception {
        when(binlist.getCreditCardDetails(any()))
                .thenReturn(CreditCardInfo.builder()
                        .country(Country.builder().name("South Africa").build())
                        .build());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/credit/submit")
                        .content(om.writeValueAsString(new CreditCardNumber(CREDIT_CARD_NUMBER)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Retrieve all mvc test")
    void retrieveAll() throws Exception {
        when(creditCardRepository.findById(any())).
                thenReturn(Optional.of(new CreditCardNumber(CREDIT_CARD_NUMBER)));

        mvc.perform(get("/credit/retrieveAll"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Retrieve mvc test")
    void retrieve() throws Exception {
        when(creditCardRepository.findById(CREDIT_CARD_NUMBER)).
                thenReturn(Optional.of(new CreditCardNumber(CREDIT_CARD_NUMBER)));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/credit/retrieve")
                        .content(om.writeValueAsString(new CreditCardNumber(CREDIT_CARD_NUMBER)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Retrieve non existent record mvc test")
    void retrieveDoesNotExist() throws Exception {
        mvc.perform(post("/credit/retrieve/" + Long.MAX_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}