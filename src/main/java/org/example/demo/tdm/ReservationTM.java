package org.example.demo.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationTM {
    private String reservation_id;
    private String customer_id;
    private String date;
    private String time;

}



