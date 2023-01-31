package com.mycompany.flights.database.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ObjectDB<T> {

    boolean insertObject(PreparedStatement stmt) throws SQLException;

    T executeObject(PreparedStatement stmt) throws SQLException;

    ArrayList<T> executeList(PreparedStatement stmt) throws SQLException;

    T fillObject(ResultSet rs) throws SQLException;

    PreparedStatement getObjectByID(long id) throws SQLException;

    PreparedStatement getAllObjects() throws SQLException;
}
