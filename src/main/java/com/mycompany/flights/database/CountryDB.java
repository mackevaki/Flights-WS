package com.mycompany.flights.database;

import com.mycompany.flights.database.abstracts.AbstractObjectDB;
import com.mycompany.flights.spr.objects.Country;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDB extends AbstractObjectDB<Country> {
    public final static String TABLE_SPR_COUNTRY = "spr_country";

    private CountryDB() {
        super(TABLE_SPR_COUNTRY);
    }
    private static CountryDB instance;

    public static CountryDB getInstance() {
        if (instance == null) {
            instance = new CountryDB();
        }
        return instance;
    }

    @Override
    public Country fillObject(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getLong("id"));
        country.setCode(rs.getString("code"));
        country.setFlag(rs.getBytes("flag"));
        country.setDesc(rs.getString("desc"));
        country.setName(rs.getString("name"));
        return country;
    }
}
