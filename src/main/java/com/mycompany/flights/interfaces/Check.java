package com.mycompany.flights.interfaces;

import com.mycompany.flights.objects.Reservation;

public interface Check {
    Reservation checkReservationByCode(String code);

//    Reservation checkReservationByDateReserv(String docNumber);
//
//    Reservation checkReservationByDateReserv(long date);
//
//    Reservation checkReservationByFamilyName(String familyName);

}
