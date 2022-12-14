package com.abc.promotion.controller;

import com.abc.promotion.constants.ValidationConstants;
import com.abc.promotion.domain.Promotion;
import com.abc.promotion.service.PromoService;
import com.abc.promotion.validator.PromoControllerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/promotion")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @Autowired
    private PromoControllerValidator promoControllerValidator;

    @PutMapping
    public ResponseEntity<String> addPromotion(@Valid @RequestBody Promotion promotion){
        if(ValidationConstants.STATUS_INVALID
                .equalsIgnoreCase(promoControllerValidator.validatePromoRequest(promotion)))
            return ResponseEntity.badRequest().body("INVALID REQUEST");
        String status = promoService.addPromotion(promotion);
        return ResponseEntity.ok(status);
    }
}
