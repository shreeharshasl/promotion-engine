package com.abc.promotion.config;

import com.abc.promotion.domain.Promotion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PromotionContainerConfig {

    @Bean("promotionContainer")
    public Map<String, Promotion> getContainer(){
        return new HashMap<>();
    }
}
