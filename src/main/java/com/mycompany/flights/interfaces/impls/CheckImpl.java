package com.mycompany.flights.interfaces.impls;

import com.mycompany.flights.database.ReservationDB;
import com.mycompany.flights.interfaces.Check;
import com.mycompany.flights.objects.Reservation;
import com.mycompany.flights.utils.GMTCalendar;

import java.sql.SQLException;
import java.util.Calendar;

public class CheckImpl implements Check {

    private ReservationDB reservationDB = ReservationDB.getInstance();

    @Override
    public Reservation checkReservationByCode(String code) {
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByCode(code));
            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation checkReservationByDateReserv(long date) {
        Calendar c = GMTCalendar.getInstance();
        c.setTimeInMillis(date);
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByDateReserv(c));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public Reservation checkReservationByDocument(String doc) {
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByDocumentNumber(doc));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public Reservation checkReservationByFamily(String family) {
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByFamilyName(family));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }
}
