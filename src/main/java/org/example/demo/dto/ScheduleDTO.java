package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

    @Getter
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor

    public class ScheduleDTO {
        private String employee_id;
        private String schedule_id;
        private String day;
        private String shift;

    }


