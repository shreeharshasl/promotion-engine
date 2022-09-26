package com.abc.promotion.service;

import com.abc.promotion.domain.Promotion;

import java.util.List;

public interface PromoService {

    String addPromotion(Promotion promotion);
    List<Promotion> getPromotions();
}
