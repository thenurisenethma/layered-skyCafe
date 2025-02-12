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
public class ReservationDTO {
    private String reservation_id;
    private String customer_id;
    private String date;
    private String time;

}
