package com.abc.promotion.service;

import com.abc.promotion.constants.PromoTypeConstants;
import com.abc.promotion.domain.Item;
import com.abc.promotion.domain.PromoItem;
import com.abc.promotion.domain.Promotion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PromoRuleServiceImpl implements PromoRuleService{

    @Autowired
    @Qualifier("promotionContainer")
    private Map promotionContainer;
    /**
     * @param cartLineItems
     * @return
     */
    @Override
    public Double applyPromo(List<Item> cartLineItems) {
        log.debug("Applying promo for cartLineItems:{}",cartLineItems);
        Double amount = 0.0;
        List<Promotion> listOfPromotions
                = (List<Promotion>) promotionContainer.values()
                .stream().collect(Collectors.toList());
        for (Promotion promotion: listOfPromotions) {
            switch (promotion.getPromoType()){
                case PromoTypeConstants.SAME_TYPE: amount += applySameTypePromo(promotion, cartLineItems);
                    break;
                case PromoTypeConstants.MIXED_TYPE: amount += applyMixedTypePromo(promotion, cartLineItems);
                    break;
            }
        }
        amount+=cartLineItems.stream().mapToDouble(item->item.getQty()*item.getUnitPrice()).sum();

        return amount;
    }

    private Double applyMixedTypePromo(Promotion promotion, List<Item> cartLineItems) {
        Double amount = 0.0;
        List<PromoItem> promoItems = promotion.getPromoItemList();
        while(true){
            boolean allPresent = true;
            for(PromoItem promoItem: promoItems){

                Optional<Item> itemFound = cartLineItems.stream().filter(item->
                {
                    boolean b = item.getSkuId().equalsIgnoreCase(promoItem.getSkuId())
                            && promoItem.getCount() <= item.getQty();return b;

                }).findAny();
                if(itemFound.isEmpty())allPresent=false;
                if(!allPresent)break;
            }
            if(!allPresent)break;
            else {
                amount+=promotion.getPromoAmount();
                for(PromoItem promoItem: promoItems){

                    for (Item item: cartLineItems) {
                        if(item.getSkuId().equalsIgnoreCase(promoItem.getSkuId()))
                            item.setQty(item.getQty()-promoItem.getCount());
                    }
                }
            }
        }

        return amount;
    }

    private Double applySameTypePromo(Promotion promotion, List<Item> cartLineItems) {
        Double amount = 0.0;
        String skuId = promotion.getPromoItemList().get(0).getSkuId();
        for (Item item: cartLineItems) {
            if(item.getSkuId().equalsIgnoreCase(skuId)){
                amount+=(item.getQty()/promotion.getPromoItemList().get(0).getCount())*promotion.getPromoAmount();
                item.setQty(item.getQty()%promotion.getPromoItemList().get(0).getCount());
            }
        }
        return amount;
    }
}
