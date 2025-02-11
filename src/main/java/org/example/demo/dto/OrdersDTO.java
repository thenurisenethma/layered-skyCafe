package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDTO {
    private String orders_id;
    private String customer_id;
    private String name;
    private int qty;


}
