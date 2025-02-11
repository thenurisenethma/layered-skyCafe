package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ProductIngredientDetailDTO {
    private String product_name;
    private String ingredient_id;
    private int qty_for_one_in_gram_or_miligram;
}
