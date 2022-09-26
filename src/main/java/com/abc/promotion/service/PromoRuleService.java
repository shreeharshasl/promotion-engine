package com.abc.promotion.service;

import com.abc.promotion.domain.Item;

import java.util.List;

public interface PromoRuleService {
    Double applyPromo(List<Item> cartLineItems);
}
