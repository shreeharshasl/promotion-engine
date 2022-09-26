package com.abc.promotion.controller;

import com.abc.promotion.domain.PromoItem;
import com.abc.promotion.domain.Promotion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PromoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void putPromotion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/promotion")
                .content(asJsonString(Promotion.builder()
                        .promoId("1")
                        .promoType("SAME")
                        .promoAmount(20.0)
                        .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    public static String asJsonString(final Object obj){
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}