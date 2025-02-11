package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class SupplierDTO {
    private String supplier_id;
    private String name;
    private String email;
    private int contact;
    private String address;

}
