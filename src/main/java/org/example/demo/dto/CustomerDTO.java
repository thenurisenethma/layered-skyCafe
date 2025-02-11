package org.example.demo.dto;
//3
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CustomerDTO {
    private String customer_id;
    private String name;
    private String email;
    private String contact;

}
