package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.ScheduleDAO;
import org.example.demo.dto.ScheduleDTO;
import org.example.demo.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {

    @Override
    public boolean save(Schedule schedule) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO schedule values (?,?,?,?)",schedule.getSchedule_id(),schedule.getEmployee_id(),schedule.getDay(),schedule.getShift());
    }

    @Override
    public boolean delete(String schedule_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from schedule where schedule_id = ?",schedule_id);
    }

    @Override
    public ArrayList<Schedule> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from schedule");
        ArrayList<Schedule> getSchedule = new ArrayList();
        while (rst.next()) {
            getSchedule.add(new Schedule(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
       return getSchedule;
    }

    @Override
    public String getNextScheduleId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('S', LPAD(IFNULL(MAX(SUBSTRING(supplier_id, 2)) + 1, 1), 3, '0')) AS next_supplier_id FROM supplier;");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(Schedule schedule) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE schedule set employee_id=?,day=?,shift=? where schedule_id=?",schedule.getEmployee_id(),schedule.getDay(),schedule.getShift(),schedule.getSchedule_id());
    }

}
