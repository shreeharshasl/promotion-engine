package com.abc.promotion.repository;

import com.abc.promotion.domain.Promotion;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PromotionRepository {
    String save(Promotion promotion);
    Map getPromotions();
}
