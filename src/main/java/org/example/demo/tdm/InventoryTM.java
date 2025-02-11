package org.example.demo.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryTM {
    private String inventory_id;
    private String ingredient_id;
    private String supplier_id;
    private String updated_date;
    private String exp_date;
    private int qty_available_in_gram_or_miligram;

}
