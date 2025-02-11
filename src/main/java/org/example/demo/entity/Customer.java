package org.example.demo.entity;
//4
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Customer {
    private String customer_id;
    private String name;
    private String email;
    private String contact;

}
