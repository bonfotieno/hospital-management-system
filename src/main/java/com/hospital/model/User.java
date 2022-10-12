package com.hospital.model;

import com.hospital.services.Entity;
import com.hospital.services.SQLdb;
import com.hospital.services.MySQLdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Entity {

    private int user_id;
    private String username;
    private String password;
    private String userType;

    static ResultSet resultSet;
    private static final String tableName = "users";

    private static final Map<String, Object> entitiesMap = new HashMap<String, Object>(){{
        put("user_id", null);
        put("Username", "");
        put("Password", "");
        put("UserType", "");
    }};

    public User() throws SQLException {
        super(entitiesMap, tableName);
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        entitiesMap.put("Id", user_id);
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        entitiesMap.put("Username", username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        entitiesMap.put("Password", password);
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        entitiesMap.put("UserType", userType);
        this.userType = userType;
    }

    public static List<User> displayAll(ResultSet resultSet) throws SQLException {
        List<User> studentList = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setUser_id(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setUserType(resultSet.getString("userType"));
            studentList.add(user);
        }
        return studentList;
    }

    @Override
    public String toString() {
        return "Login{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
