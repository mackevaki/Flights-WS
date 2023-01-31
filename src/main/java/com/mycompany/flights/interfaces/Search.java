package com.mycompany.flights.interfaces;

import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.spr.objects.City;

import java.util.List;

public interface Search {
    List<Flight> searchFlight(long date, City cityFrom, City cityTo);

    List<City> getAllCities();
}
