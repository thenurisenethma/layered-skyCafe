package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private String employee_id;
    private String user_id;
    private String name;
    private String contact;
    private String role;

}
