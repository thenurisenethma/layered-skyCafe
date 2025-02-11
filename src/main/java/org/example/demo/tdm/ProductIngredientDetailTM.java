package org.example.demo.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductIngredientDetailTM {
    private String product_name;
    private String ingredient_id;
    private int qty_for_one_in_gram_or_miligram;
}
