package com.mycompany.flights.interfaces.sei;

import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.objects.Passenger;
import com.mycompany.flights.objects.Reservation;
import com.mycompany.flights.spr.objects.City;
import com.mycompany.flights.spr.objects.Place;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;
import java.util.ArrayList;

@WebService(name = "FlightWS", targetNamespace = "http://ws.flights.mycompany.com/")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL)
public interface FlightSEI {
    Reservation checkReservationByCode(@WebParam(name = "code") String code);

    ArrayList<City> getAllCities();

    ArrayList<Flight> searchFlight(@WebParam(name = "date") long date, @WebParam(name = "cityFrom") City cityFrom, @WebParam(name = "cityTo") City cityTo);
   
    boolean buyTicket(@WebParam(name = "flight") Flight flight, @WebParam(name = "place") Place place, @WebParam(name = "passenger") Passenger passenger, @WebParam(name = "addInfo") String addInfo);
}
