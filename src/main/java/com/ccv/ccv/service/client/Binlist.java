package com.ccv.ccv.service.client;

import com.ccv.ccv.model.CreditCardInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@FeignClient(name = "Binlist", url = "https://lookup.binlist.net")
public interface Binlist {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.GET, value = "/{creditCardNumber}")
    CreditCardInfo getCreditCardDetails(@PathVariable(name = "creditCardNumber") @NotNull String creditCardNumber);
}
