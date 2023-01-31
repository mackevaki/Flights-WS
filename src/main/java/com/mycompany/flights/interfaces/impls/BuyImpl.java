package com.mycompany.flights.interfaces.impls;

import com.mycompany.flights.database.ReservationDB;
import com.mycompany.flights.interfaces.Buy;
import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.objects.Passenger;
import com.mycompany.flights.objects.Reservation;
import com.mycompany.flights.spr.objects.Place;
import com.mycompany.flights.utils.GMTCalendar;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class BuyImpl implements Buy {
    @Override
    public boolean buyTicket(Flight flight, Place place, Passenger passenger, String addInfo) {
        Calendar date = GMTCalendar.getInstance();
        date.setTime(new Date());

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPlace(place);
        reservation.setAddInfo(addInfo);
        reservation.setCode(UUID.randomUUID().toString());
        reservation.setReserveDateTime(date);
        reservation.setPassenger(passenger);

        try {
            ReservationDB.getInstance().insertObject(ReservationDB.getInstance().getInsertStmt(reservation));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
