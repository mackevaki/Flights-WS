package com.mycompany.flights.servlets;

import com.mycompany.flights.database.ReservationDB;
import com.mycompany.flights.objects.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "TestSearch", value = "/TestSearch")
public class TestSearch extends HttpServlet {
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TestSearch</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet TestSearch at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");



//            Aircraft a = AircraftDB.getInstance().getAircraft(1);
//            City a = CityDB.getInstance().getCity(2);
//            System.out.println(a.getName());
//            City c1 = CityDB.getInstance().getCity(2);
//            City c2 = CityDB.getInstance().getCity(7);
//            City c3 = CityDB.getInstance().getCity(7);
//
//            long date = 1674959340000L;
//
//            ArrayList<Flight> list = FlightDB.getInstance().getFlights(date, c1, c2);
//
//            for (Flight flight : list) {
//                System.out.println(flight.getAircraft().getName() + " " + flight.getDateDepart().getTimeInMillis() + " " + flight.getCityFrom().getName());
//            }

//            Flight flight = FlightDB.getInstance().executeObject(FlightDB.getInstance().getObjectByID(1));
//
//            Place place = PlaceDB.getInstance().executeObject(PlaceDB.getInstance().getObjectByID(2));
//
//            Passenger passenger = PassengerDB.getInstance().executeObject(PassengerDB.getInstance().getObjectByID(1));
//
//            Calendar date = Calendar.getInstance();
//            date.setTimeInMillis(1674959340000L);
//
//            Reservation reserv = new Reservation();
//            reserv.setAddInfo("Без обеда");
//            reserv.setCode(UUID.randomUUID().toString());
//            reserv.setPassenger(passenger);
//            reserv.setReserveDateTime(date);
//            reserv.setPlace(place);
//            reserv.setFlight(flight);

//            ReservationDB.getInstance().insert(ReservationDB.getInstance().getInsertStmt(reserv));

//            reserv = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByCode("eb08ce68-d1c9-4921-a3af-a464215a4859"));
//            System.out.println(reserv.getFlight().getAircraft().getName());
//
//            Calendar c = Calendar.getInstance();
//            c.setTimeInMillis(1674959340000L);
//
//            reserv = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByDateReserv(date));
//            System.out.println(reserv.getFlight().getAircraft().getName());
//
//            reserv = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByDocumentNumber("864388748"));
//            System.out.println(reserv.getFlight().getAircraft().getName());
//
//            reserv = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByFamilyName("Doe"));
//            System.out.println(reserv.getFlight().getAircraft().getName());

            Reservation reserv = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByFamilyName("Cage"));
            System.out.println(reserv.getFlight().getAircraft().getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
