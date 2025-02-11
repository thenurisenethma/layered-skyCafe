package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.ScheduleDTO;
import org.example.demo.entity.Schedule;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScheduleBO extends SuperBO {
    boolean saveSchedule(ScheduleDTO scheduleDTO) throws SQLException, ClassNotFoundException;

    boolean deleteSchedule(String schedule_id) throws SQLException, ClassNotFoundException;

    ArrayList<ScheduleDTO> getAllSchedules() throws SQLException, ClassNotFoundException;

    String getNextScheduleId() throws SQLException, ClassNotFoundException;

    boolean updateSchedule(ScheduleDTO scheduleDTO) throws SQLException, ClassNotFoundException;
}
