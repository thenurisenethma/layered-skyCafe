package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Schedule;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScheduleDAO extends CrudDAO<Schedule> {
//     boolean save(Schedule schedule) throws SQLException, ClassNotFoundException;
//
//    boolean delete(String schedule_id) throws SQLException, ClassNotFoundException;
//
//    ArrayList<Schedule> getAll() throws SQLException, ClassNotFoundException;

    String getNextScheduleId() throws SQLException, ClassNotFoundException;

//    boolean update(Schedule schedule) throws SQLException, ClassNotFoundException;
}
