package com.ccv.ccv.repository;

import com.ccv.ccv.model.CreditCardNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardNumber, String> {}


