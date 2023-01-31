package com.mycompany.flights.interfaces;

import com.mycompany.flights.objects.Reservation;

public interface Check {
    Reservation checkReservationByCode(String code);

    Reservation checkReservationByDateReserv(long date);

    Reservation checkReservationByDocument(String doc);

    Reservation checkReservationByFamily(String family);
}
