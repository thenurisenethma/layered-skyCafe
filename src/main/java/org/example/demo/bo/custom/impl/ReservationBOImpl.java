package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.ReservationBO;

import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.ReservationDAO;
import org.example.demo.dto.ReservationDTO;
import org.example.demo.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;


public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    public ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        ArrayList<Reservation> reservations = reservationDAO.getAll();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(
                    reservation.getReservation_id(),
                    reservation.getCustomer_id(),
                    reservation.getDate(),
                    reservation.getTime()
            ));
        }
        return reservationDTOS;
    }

    @Override
    public String getNextReservationId() throws SQLException, ClassNotFoundException {
        return reservationDAO.getNextReservationId();
    }

    @Override
    public boolean delete (String reservationId) throws SQLException, ClassNotFoundException {
        return reservationDAO.delete(reservationId);

    }

    @Override
    public boolean save (ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return reservationDAO.save(new Reservation(reservationDTO.getReservation_id(),reservationDTO.getCustomer_id(),reservationDTO.getDate(),reservationDTO.getTime()));

    }

    @Override
    public Boolean update (ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException {
        return reservationDAO.update(new Reservation(reservationDTO.getReservation_id(),reservationDTO.getCustomer_id(),reservationDTO.getDate(),reservationDTO.getTime()));
    }


}
