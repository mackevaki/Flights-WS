package com.mycompany.flights.interfaces;

import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.spr.objects.City;
import java.util.ArrayList;

public interface Search {
    ArrayList<Flight> searchFlight(long date, City cityFrom, City cityTo);
    
    ArrayList<City> getAllCities();
}
