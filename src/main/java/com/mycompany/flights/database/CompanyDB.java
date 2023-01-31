package com.mycompany.flights.database;

import com.mycompany.flights.database.abstracts.AbstractObjectDB;
import com.mycompany.flights.spr.objects.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDB extends AbstractObjectDB<Company> {
    public final static String TABLE_SPR_COMPANY = "avia.spr_company";

    private static CompanyDB instance;

    private CompanyDB() {
        super(TABLE_SPR_COMPANY);
    }

    public static CompanyDB getInstance() {
        if (instance == null) {
            instance = new CompanyDB();
        }
        return instance;
    }

    @Override
    public Company fillObject(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getLong("id"));
        company.setName(rs.getString("name"));
        company.setDesc(rs.getString("desc"));
        return company;
    }
}
