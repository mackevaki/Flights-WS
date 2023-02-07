package com.mycompany.flights.ws;

import com.mycompany.flights.interfaces.Buy;
import com.mycompany.flights.interfaces.Check;
import com.mycompany.flights.interfaces.Search;
import com.mycompany.flights.interfaces.impls.BuyImpl;
import com.mycompany.flights.interfaces.impls.CheckImpl;
import com.mycompany.flights.interfaces.impls.SearchImpl;
import com.mycompany.flights.objects.Flight;
import com.mycompany.flights.objects.Passenger;
import com.mycompany.flights.objects.Reservation;
import com.mycompany.flights.spr.objects.City;
import com.mycompany.flights.spr.objects.Place;
import com.mycompany.flights.utils.GMTCalendar;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.ArrayList;
import java.util.Calendar;

@MTOM
@WebService(serviceName = "SearchWS")
@BindingType(value = SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public class SearchWS implements Search, Buy, Check {
    private SearchImpl searchImpl = new SearchImpl();
    private BuyImpl buyImpl = new BuyImpl();
    private CheckImpl checkImpl = new CheckImpl();

    @Override
    public ArrayList<Flight> searchFlight(long date, City cityFrom, City cityTo) {
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
    public boolean buyTicket(Flight flight, Place place, Passenger passenger, String addInfo) {
        boolean result = false;

        result = buyImpl.buyTicket(flight, place, passenger, addInfo);

        return result;
    }

    @Override
    public Reservation checkReservationByCode(String code) {
        return checkImpl.checkReservationByCode(code);
    }
}
