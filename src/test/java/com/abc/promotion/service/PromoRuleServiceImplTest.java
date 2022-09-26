package com.abc.promotion.service;

import com.abc.promotion.constants.PromoTypeConstants;
import com.abc.promotion.domain.Item;
import com.abc.promotion.domain.PromoItem;
import com.abc.promotion.domain.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PromoRuleServiceImplTest {

    @InjectMocks
    private PromoRuleServiceImpl promoRuleService;

    private Map<String, Promotion> promotionContainer;

    /**
     * Unit price for SKU IDs
     * A      50
     * B      30
     * C      20
     * D      15
     *
     * Active Promotions
     * 3 of A's for 130
     * 2 of B's for 45
     * C & D for 30
     */
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        promotionContainer = new HashMap();
        Promotion p1 = Promotion.builder()
                .promoId("1")
                .promoType(PromoTypeConstants.SAME_TYPE)
                .promoAmount(130.0)
                .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                .build();
        Promotion p2 = Promotion.builder()
                .promoId("2")
                .promoType(PromoTypeConstants.SAME_TYPE)
                .promoAmount(45.0)
                .promoItemList(Arrays.asList(PromoItem.builder().skuId("B").count(2).build()))
                .build();
        PromoItem pi1= PromoItem.builder().skuId("C").count(1).build();
        PromoItem pi2= PromoItem.builder().skuId("D").count(1).build();
        Promotion p3 = Promotion.builder()
                .promoId("2")
                .promoType(PromoTypeConstants.SAME_TYPE)
                .promoAmount(45.0)
                .promoItemList(Arrays.asList(pi1,pi2))
                .build();
        promotionContainer.put(p1.getPromoId(),p1);
        promotionContainer.put(p2.getPromoId(),p2);
        promotionContainer.put(p3.getPromoId(),p3);
        ReflectionTestUtils.setField(promoRuleService,"promotionContainer",promotionContainer);
    }
    @Test
    /**
     * Scenario A
     * 1 * A     50
     * 1 * B     30
     * 1 * C     20
     * ======
     * Total     100
     */
    void applyPromo_ScenarioA() {
        List<Item> cartLineItems = prepareTestDataForScenario1();
        Double actualValue = promoRuleService.applyPromo(cartLineItems);
        Assertions.assertEquals(100,actualValue);
    }

    @Test
    /**
     * Scenario B
     * 5 * A     130 + 2*50
     * 5 * B     45 + 45 + 30
     * 1 * C     20
     * ======
     * Total     370
     */
    void applyPromo_ScenarioB() {
        List<Item> cartLineItems = prepareTestDataForScenario2();
        Double actualValue = promoRuleService.applyPromo(cartLineItems);
        Assertions.assertEquals(370,actualValue);
    }

    private List<Item> prepareTestDataForScenario2() {
        List<Item> cartLineItems = new ArrayList<>();
        cartLineItems.add(Item.builder().skuId("A").unitPrice(50.0).qty(5).build());
        cartLineItems.add(Item.builder().skuId("B").unitPrice(30.0).qty(5).build());
        cartLineItems.add(Item.builder().skuId("C").unitPrice(20.0).qty(1).build());
        return  cartLineItems;
    }

    private List<Item> prepareTestDataForScenario1() {
        List<Item> cartLineItems = new ArrayList<>();
        cartLineItems.add(Item.builder().skuId("A").unitPrice(50.0).qty(1).build());
        cartLineItems.add(Item.builder().skuId("B").unitPrice(30.0).qty(1).build());
        cartLineItems.add(Item.builder().skuId("C").unitPrice(20.0).qty(1).build());
        return  cartLineItems;
    }
}