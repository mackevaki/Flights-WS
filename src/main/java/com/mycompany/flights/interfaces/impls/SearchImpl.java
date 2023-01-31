package com.mycompany.flights.interfaces.impls;

import com.mycompany.flights.database.CityDB;
import com.mycompany.flights.database.FlightDB;
import com.mycompany.flights.interfaces.Search;
import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.spr.objects.City;
import com.mycompany.flights.utils.GMTCalendar;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebService(serviceName = "FlightService")
public class SearchImpl implements Search {
    private final FlightDB flightDB = FlightDB.getInstance();
    private final CityDB cityDB = CityDB.getInstance();

    @Override
    @WebMethod(operationName = "searchFlight")
    public List<Flight> searchFlight(@WebParam(name = "date") long date, @WebParam(name = "cityFrom") City cityFrom, @WebParam(name = "cityTo") City cityTo) {
        List<Flight> list = new ArrayList<>();
        Calendar calendar = GMTCalendar.getInstance();
        calendar.setTimeInMillis(date);
        try {
            list.addAll(flightDB.executeList(flightDB.getStmt(calendar, cityFrom, cityTo)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list; 
    }

    @Override
    @WebMethod(operationName = "getAllCities")
    public List<City> getAllCities() {
        List<City> list = new ArrayList<>();
        try {
            list.addAll(cityDB.executeList(cityDB.getAllObjects()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //
//    private boolean hasFreePlaces(Flight flight){
//        return flight.isExistFreePlaces();
//    }
    
}
