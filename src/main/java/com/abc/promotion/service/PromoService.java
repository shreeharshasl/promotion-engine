package com.abc.promotion.service;

import com.abc.promotion.domain.Promotion;

import java.util.List;
import java.util.Map;

public interface PromoService {

    String addPromotion(Promotion promotion);
    Map getPromotions();
}
