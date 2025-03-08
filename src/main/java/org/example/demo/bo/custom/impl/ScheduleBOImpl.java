package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.ScheduleBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.ScheduleDAO;
import org.example.demo.dto.ScheduleDTO;
import org.example.demo.entity.Customer;
import org.example.demo.entity.Schedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    @Override
    public boolean save(ScheduleDTO scheduleDTO) throws SQLException, ClassNotFoundException {
        return scheduleDAO.save(new Schedule(scheduleDTO.getEmployee_id(),scheduleDTO.getSchedule_id(),scheduleDTO.getDay(),scheduleDTO.getShift()));

    }

    @Override
    public boolean delete(String schedule_id) throws SQLException, ClassNotFoundException {
        return scheduleDAO.delete(schedule_id);
    }

    @Override
    public ArrayList<ScheduleDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        ArrayList<Schedule> schedules = scheduleDAO.getAll();
        for (Schedule schedule: schedules ){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTOS.add(new ScheduleDTO(schedule.getEmployee_id(),schedule.getSchedule_id(),schedule.getDay(),schedule.getShift()));
        }
        return scheduleDTOS;
    }

    @Override
    public String getNextScheduleId() throws SQLException, ClassNotFoundException {
        return scheduleDAO.getNextScheduleId();
    }

    @Override
    public boolean update(ScheduleDTO scheduleDTO) throws SQLException, ClassNotFoundException {
        return scheduleDAO.update(new Schedule(scheduleDTO.getEmployee_id(),scheduleDTO.getSchedule_id(),scheduleDTO.getDay(),scheduleDTO.getShift()));
    }
}
