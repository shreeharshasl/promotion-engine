package com.abc.promotion.validator;

import com.abc.promotion.domain.PromoItem;
import com.abc.promotion.domain.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PromoControllerValidatorImplTest {

    @InjectMocks
    private PromoControllerValidatorImpl promoControllerValidator;

    private Promotion promotion;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        promotion = Promotion.builder()
                .promoId("1")
                .promoType("SAME")
                .promoAmount(20.0)
                .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                .build();
    }
    @Test
    void validatePromoRequest() {
        String validationReponse = promoControllerValidator.validatePromoRequest(promotion);
        Assertions.assertEquals("SUCCESS",validationReponse);
    }
}