package com.abc.promotion.service;

import com.abc.promotion.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PromoRuleServiceImpl implements PromoRuleService{

    @Autowired
    @Qualifier("promotionContainer")
    private Map promotionContainer;
    /**
     * @param cartLineItems
     * @return
     */
    @Override
    public Double applyPromo(List<Item> cartLineItems) {
        return null;
    }
}
