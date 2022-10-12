package com.hospital.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface SQLdb<T extends Entity, I extends Connection> {

    String createInsertQuery();
    String createUpdateQuery(Map<String, Object> entryMap);
    public ResultSet fetchAll() throws SQLException;
    public ResultSet fetchOne() throws SQLException;
    public void update();
    String createSelectQuery();
    public String createSelectWithWhereClauseQuery();

    public String createSelectOneQuery();
    boolean executeQuery(String query);
    public void save();
    ResultSet executeReadQuery(String query) throws SQLException;
}
