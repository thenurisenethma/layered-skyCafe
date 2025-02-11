package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    private String order_detail_id;
    private String name;
    private String order_id;
    private int qty;
    private double total_price;
    private double price_for_one;


    public OrderDetailDTO(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }
}
