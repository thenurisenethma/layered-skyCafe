package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException;
    String getNextReservationId() throws SQLException, ClassNotFoundException;

    boolean deleteReservation(String reservationId) throws SQLException, ClassNotFoundException;

    boolean saveReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    Boolean updateReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

}

