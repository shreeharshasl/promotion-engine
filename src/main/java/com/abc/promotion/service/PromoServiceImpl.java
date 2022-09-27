package com.abc.promotion.service;

import com.abc.promotion.domain.Promotion;
import com.abc.promotion.repository.PromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PromoServiceImpl implements PromoService{

    @Autowired
    private PromotionRepository promotionRepository;
    /**
     * @param promotion
     * @return
     */
    @Override
    public String addPromotion(Promotion promotion) {
        try {
            promotionRepository.save(promotion);
            log.debug("Inside Promo Service Add Method Promotion: {}", promotion);
            return "Success";
        }
        catch (Exception e){
            log.error(e.getMessage());
            return "Failure";
        }
    }

    /**
     * @return
     */
    @Override
    public Map getPromotions() {
        return promotionRepository.getPromotions();
    }
}
