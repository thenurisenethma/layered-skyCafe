package org.example.demo.dao.custom.impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.ReservationDAO;
import org.example.demo.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation");
        ArrayList<Reservation> reservations = new ArrayList<>();
        while (rst.next()) {
            reservations.add(new Reservation(
                    rst.getString("reservation_id"),
                    rst.getString("customer_id"),
                    rst.getString("date"),
                    rst.getString("time")
            ));
        }
        return reservations;
    }

    @Override
    public String getNextReservationId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CONCAT('R', LPAD(IFNULL(MAX(SUBSTRING(reservation_id, 2)) + 1, 1), 3, '0')) AS next_reservation_id FROM reservation;");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String reservationId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from reservation where reservation_id=?",reservationId);

    }

    @Override
    public boolean save(Reservation reservation) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation VALUES (?, ?, ?, ?)", reservation.getReservation_id(), reservation.getCustomer_id(), reservation.getDate(), reservation.getTime());
    }

    @Override
    public Boolean update(Reservation reservation) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product set customer_id=?,date=?,time=? where reservation_id=?",reservation.getCustomer_id(),reservation.getDate(),reservation.getTime(),reservation.getReservation_id());
    }

}
