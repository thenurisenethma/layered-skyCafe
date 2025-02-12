package org.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Reservation {
    private String reservation_id;
    private String customer_id;
    private String date;
    private String time;

}
