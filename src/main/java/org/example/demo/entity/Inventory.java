package org.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {
    private String inventory_id;
    private String ingredient_id;
    private String supplier_id;
    private String updated_date;
    private String exp_date;
    private int qty_available_in_gram_or_miligram;

}
