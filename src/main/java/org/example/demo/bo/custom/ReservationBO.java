package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAll () throws SQLException, ClassNotFoundException;
    String getNextReservationId() throws SQLException, ClassNotFoundException;

    boolean delete (String reservationId) throws SQLException, ClassNotFoundException;

    boolean save (ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    Boolean update (ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

}

