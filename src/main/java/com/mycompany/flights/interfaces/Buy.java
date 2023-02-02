package com.mycompany.flights.interfaces;

import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.objects.Passenger;
import com.mycompany.flights.spr.objects.Place;

public interface Buy {
    boolean buyTicket(Flight flight, Place place, Passenger passenger, String addInfo);

}
