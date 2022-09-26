package com.abc.promotion.validator;

import com.abc.promotion.constants.ValidationConstants;
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
        String status = ValidationConstants.STATUS_VALID;
        if(promotion != null && promotion.getPromoType().equalsIgnoreCase("SAME")
                && promotion.getPromoItemList().size()>1) status = ValidationConstants.STATUS_INVALID;
        return status;
    }
}
