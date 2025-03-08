package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation> {
//    ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException;

    public String getNextReservationId() throws SQLException, ClassNotFoundException;

//    boolean delete(String reservationId) throws SQLException, ClassNotFoundException;
//
//    boolean save(Reservation reservation) throws SQLException, ClassNotFoundException;
//
//    Boolean update(Reservation reservation) throws SQLException, ClassNotFoundException;
}
