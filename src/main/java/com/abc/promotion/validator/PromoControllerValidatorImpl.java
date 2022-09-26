package com.abc.promotion.validator;

import com.abc.promotion.constants.ValidationConstants;
import com.abc.promotion.domain.Promotion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PromoControllerValidatorImpl implements PromoControllerValidator{
    /**
     * @param promotion
     * @return
     */
    @Override
    public String validatePromoRequest(Promotion promotion) {
        log.debug("Inside Validation Method Promotion: {}",promotion);
        String status = ValidationConstants.STATUS_VALID;
        if(promotion != null && promotion.getPromoType().equalsIgnoreCase("SAME")
                && promotion.getPromoItemList().size()>1) status = ValidationConstants.STATUS_INVALID;
        return status;
    }
}
