package org.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class Supplier {
    private String supplier_id;
    private String name;
    private String email;
    private int contact;
    private String address;

}
