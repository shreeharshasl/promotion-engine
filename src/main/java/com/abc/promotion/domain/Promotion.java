package com.abc.promotion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Promotion {
    private String promoId;

    //Represents mixed/same products promotion
    @NotBlank
    private String promoType;
    private Integer priority;

    //In case of Mixed products, list will have multiple sku's
    // with count of each item for this particular promo
    @NotEmpty
    private List<PromoItem> promoItemList;
    @NotNull
    private Double promoAmount;
}
