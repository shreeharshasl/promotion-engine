package com.abc.promotion.repository;

import com.abc.promotion.domain.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository{

    @Autowired
    @Qualifier("promotionContainer")
    private Map promotionContainer;
    /**
     * @param promotion
     * @return
     */
    @Override
    public String save(Promotion promotion) {
        promotionContainer.put(promotion.getPromoId(), promotion);
        return "Success";
    }

    /**
     * @return
     */
    @Override
    public Map getPromotions() {
        return promotionContainer;
    }
}
