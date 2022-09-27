package com.abc.promotion.validator;

import com.abc.promotion.domain.Promotion;

public interface PromoControllerValidator {
    String validatePromoRequest(Promotion promotion);
}
