package com.abc.promotion.controller;

import com.abc.promotion.domain.Promotion;
import com.abc.promotion.service.PromoService;
import com.abc.promotion.validator.PromoControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/promotion")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @Autowired
    private PromoControllerValidator promoControllerValidator;

    @PutMapping
    public ResponseEntity<String> addPromotion(@Valid @RequestBody Promotion promotion){
        return ResponseEntity.ok("Success");
    }
}
