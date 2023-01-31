package com.mycompany.flights.database.abstracts;

import com.mycompany.flights.database.AviaDB;
import com.mycompany.flights.database.interfaces.ObjectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class AbstractObjectDB<T> implements ObjectDB<T> {
    public static final int INTERVAL = 1;

    private String tableName;

    public AbstractObjectDB(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean insertObject(PreparedStatement stmt) throws SQLException {
        try {
            int result = stmt.executeUpdate();
            return result > 0;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public T executeObject(PreparedStatement stmt) throws SQLException {
        T obj = null;

        try (ResultSet rs = stmt.executeQuery()) {
            rs.next();
            if (rs.isFirst()) {
                obj = fillObject(rs);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return obj;
    }

    @Override
    public ArrayList<T> executeList(PreparedStatement stmt) throws SQLException {
        ArrayList<T> list = new ArrayList<>();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(fillObject(rs));
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return list;
    }

    @Override
    public PreparedStatement getObjectByID(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from " + tableName + " where id=?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjects() throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from " + tableName);
        return stmt;
    }

    protected void clearTime(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    protected boolean getBooleanFromInt(int num) {
        return num > 0;
    }
}
