package com.abc.promotion.validator;

import com.abc.promotion.constants.PromoTypeConstants;
import com.abc.promotion.constants.ValidationConstants;
import com.abc.promotion.domain.PromoItem;
import com.abc.promotion.domain.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                .promoType(PromoTypeConstants.SAME_TYPE)
                .promoAmount(20.0)
                .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                .build();
    }
    @Test
    void validatePromoRequestSunnyDay() {
        String validationResponse = promoControllerValidator.validatePromoRequest(promotion);
        Assertions.assertEquals(ValidationConstants.STATUS_VALID,validationResponse);
    }

    @Test
    void validatePromoRequest_Same_Promo_multipleItem_Invalid() {
        List<PromoItem> promoItems = new ArrayList<>();
        promoItems.add(PromoItem.builder().skuId("A").count(1).build());
        promoItems.add(PromoItem.builder().skuId("B").count(1).build());
        promotion.setPromoItemList(promoItems);
        String validationResponse = promoControllerValidator.validatePromoRequest(promotion);
        Assertions.assertEquals(ValidationConstants.STATUS_INVALID,validationResponse);
    }

    @Test
    void validatePromoRequest_Mixed_Promo_multipleItem_Valid() {
        List<PromoItem> promoItems = new ArrayList<>();
        promoItems.add(PromoItem.builder().skuId("A").count(1).build());
        promoItems.add(PromoItem.builder().skuId("B").count(1).build());
        promotion.setPromoItemList(promoItems);
        promotion.setPromoType(PromoTypeConstants.MIXED_TYPE);
        String validationResponse = promoControllerValidator.validatePromoRequest(promotion);
        Assertions.assertEquals(ValidationConstants.STATUS_VALID,validationResponse);
    }
}