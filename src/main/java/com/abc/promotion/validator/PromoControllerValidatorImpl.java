package com.abc.promotion.validator;

import com.abc.promotion.domain.Promotion;
import org.springframework.stereotype.Component;

@Component
public class PromoControllerValidatorImpl implements PromoControllerValidator{
    /**
     * @param promotion
     * @return
     */
    @Override
    public String validatePromoRequest(Promotion promotion) {
        return null;
    }
}
