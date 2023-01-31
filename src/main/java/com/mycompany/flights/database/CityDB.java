package com.mycompany.flights.database;

import com.mycompany.flights.database.abstracts.AbstractObjectDB;
import com.mycompany.flights.spr.objects.City;
import com.mycompany.flights.spr.objects.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDB extends AbstractObjectDB<City> {
    public final static String TABLE_SPR_CITY = "avia.spr_city";

    private static CityDB instance;
    private CityDB() {
        super(TABLE_SPR_CITY);
    }

    public static CityDB getInstance() {
        if (instance == null) {
            instance = new CityDB();
        }
        return instance;
    }

    public PreparedStatement getStmtByName(String name) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from " + TABLE_SPR_CITY + " where name=?");
        stmt.setString(1, name);
        return stmt;
    }

    @Override
    public City fillObject(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getLong("id"));
        city.setCode(rs.getString("code"));

        Country country = CountryDB.getInstance().executeObject(CountryDB.getInstance().getObjectByID(rs.getLong("country_id")));

        city.setCountry(country);
        city.setDesc(rs.getString("desc"));
        city.setName(rs.getString("name"));
        return city;
    }
}
