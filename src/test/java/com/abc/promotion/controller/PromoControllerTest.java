package com.abc.promotion.controller;

import com.abc.promotion.constants.PromoTypeConstants;
import com.abc.promotion.domain.PromoItem;
import com.abc.promotion.domain.Promotion;
import com.abc.promotion.service.PromoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PromoControllerTest {
    @MockBean
    private PromoService promoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Mockito.when(promoService.addPromotion(Mockito.any())).thenReturn("Success");
    }
    @Test
    public void putPromotion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/promotion")
                .content(asJsonString(Promotion.builder()
                        .promoId("1")
                        .promoType(PromoTypeConstants.SAME_TYPE)
                        .promoAmount(20.0)
                        .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void putPromotion_BadRequest_EmptyPromoType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/promotion")
                        .content(asJsonString(Promotion.builder()
                                .promoId("1")
                                .promoAmount(20.0)
                                .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void putPromotion_BadRequest_EmptyPromoAmount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/promotion")
                        .content(asJsonString(Promotion.builder()
                                .promoId("1")
                                .promoType(PromoTypeConstants.SAME_TYPE)
                                .promoItemList(Arrays.asList(PromoItem.builder().skuId("A").count(3).build()))
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void putPromotion_BadRequest_EmptyPromoItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/promotion")
                        .content(asJsonString(Promotion.builder()
                                .promoId("1")
                                .promoType(PromoTypeConstants.SAME_TYPE)
                                .promoAmount(20.0)
                                .promoItemList(new ArrayList<>())
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void putPromotion_BadRequest_Validator_Failure() throws Exception {
        List<PromoItem> promoItems = new ArrayList<>();
        promoItems.add(PromoItem.builder().skuId("A").count(1).build());
        promoItems.add(PromoItem.builder().skuId("B").count(1).build());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/promotion")
                        .content(asJsonString(Promotion.builder()
                                .promoId("1")
                                .promoType(PromoTypeConstants.SAME_TYPE)
                                .promoAmount(20.0)
                                .promoItemList(promoItems)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
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