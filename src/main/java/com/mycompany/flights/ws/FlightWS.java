package com.mycompany.flights.ws;

import com.mycompany.flights.annotations.ExceptionMessage;
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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import jakarta.xml.ws.soap.SOAPBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

@MTOM(enabled = true)
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
        if (date == null || date <= 0) {
            throw new ArgumentException("Дата вылета не заполнена");
        }

        checkObject(cityFrom, City.class);
        checkObject(cityTo, City.class);        

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
        checkObject(flight, Flight.class);
        checkObject(passenger, Passenger.class);
        checkObject(place, Place.class);
        
        boolean result = false;

        result = buyImpl.buyTicket(flight, place, passenger, addInfo);

        return result;
    }

    @Override
    public Reservation checkReservationByCode(String code) throws ArgumentException {
        if (code == null || code.isEmpty()) {
            throw new ArgumentException("Код бронирования пустой");
        }
        return checkImpl.checkReservationByCode(code);
    }
    
    private void checkObject(Object object, Class<?> c) throws ArgumentException {
        if (object == null & c.isAnnotationPresent(ExceptionMessage.class)) {
            throw new ArgumentException(c.getAnnotation(ExceptionMessage.class).message());
        }
        
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(XmlElement.class)) {
                try {
                    field.setAccessible(true);
                    if (field.getAnnotation(XmlElement.class).required()
                            && (field.get(object) == null || field.get(object).equals(""))) {
                        throw new ArgumentException(field.getAnnotation(ExceptionMessage.class).message());
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(FlightWS.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
    }
}
