package com.abc.promotion.controller;

import com.abc.promotion.domain.Cart;
import com.abc.promotion.domain.Item;
import com.abc.promotion.service.PromoRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/promo-rule")
public class PromoRuleController {

    @Autowired
    private PromoRuleService promoRuleService;

    @PostMapping
    public ResponseEntity<Cart> applyPromotion(@Valid @RequestBody List<Item> cartLineItems){
        Double price = promoRuleService.applyPromo(cartLineItems);
        return ResponseEntity.ok(Cart.builder().postPromoPrice(price).build());
    }
}
