package com.mycompany.flights.ws;

import com.mycompany.flights.interfaces.impls.BuyImpl;
import com.mycompany.flights.interfaces.impls.CheckImpl;
import com.mycompany.flights.interfaces.impls.SearchImpl;
import com.mycompany.flights.interfaces.sei.FlightSEI;
import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.objects.Passenger;
import com.mycompany.flights.objects.Reservation;
import com.mycompany.flights.spr.objects.City;
import com.mycompany.flights.spr.objects.Place;
import com.mycompany.flights.utils.GMTCalendar;
import exceptions.ArgumentException;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.ArrayList;
import java.util.Calendar;

@MTOM
@WebService(serviceName = "FlightWS")
@BindingType(value = SOAPBinding.SOAP12HTTP_MTOM_BINDING)
@HandlerChain(file = "h.xml")
//@WebService(endpointInterface = "com.mycompany.flights.interfaces.sei.FlightSEI")
public class FlightWS implements FlightSEI {
    private SearchImpl searchImpl = new SearchImpl();
    private BuyImpl buyImpl = new BuyImpl();
    private CheckImpl checkImpl = new CheckImpl();

    @Override
    public ArrayList<Flight> searchFlight(Long date, City cityFrom, City cityTo) throws ArgumentException {
        if (date == null || date.longValue() <= 0) {
            throw new ArgumentException("Date is empty or less than zero");
        }

        if (cityFrom == null) {
            throw new ArgumentException("City From is empty");
        }

        if (cityTo == null) {
            throw new ArgumentException("City To is empty");
        }

        ArrayList<Flight> list = new ArrayList<>();
        Calendar c = GMTCalendar.getInstance();
        c.setTimeInMillis(date);

        list.addAll(searchImpl.searchFlight(date, cityFrom, cityTo));

        return list;
    }

    @Override
    public ArrayList<City> getAllCities() {
        ArrayList<City> list = new ArrayList<>();
        list.addAll(searchImpl.getAllCities());
        return list;
    }

    @Override
    public boolean buyTicket(Flight flight, Place place, Passenger passenger, String addInfo) throws ArgumentException {
        if (flight == null) {
            throw new ArgumentException("Flight object is empty");
        }

        if (passenger == null) {
            throw new ArgumentException("Passenger object is empty");
        }

        if (place == null) {
            throw new ArgumentException("Place object is empty");
        }

        boolean result = false;

        result = buyImpl.buyTicket(flight, place, passenger, addInfo);

        return result;
    }

    @Override
    public Reservation checkReservationByCode(String code) throws ArgumentException {
        if (code == null || code.isEmpty()) {
            throw new ArgumentException("Code is empty");
        }
        return checkImpl.checkReservationByCode(code);
    }
}
