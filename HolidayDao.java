package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HolidayDao {


    String url = "C:\\Users\\gavri\\Desktop\\SQL_course\\Calander.db";

    Connection connection = getConnection("jdbc:sqlite:"+url);
    Statement statement = getStatement();

    public Connection getConnection(String url) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public Statement getStatement() {
        try {
            this.statement = this.connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }

    public HolidayPojo get(int id){
        HolidayPojo holiday = null;
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Holidays WHERE id = " + id+";");
            rs.next();
            holiday =new HolidayPojo(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("day"),
                    rs.getInt("month"),
                    rs.getInt("len")
                    );
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return holiday;
    }

    public List<HolidayPojo> getAll(){
        List<HolidayPojo> holidays = new ArrayList<>();
        try {
            ResultSet rs =  statement.executeQuery("SELECT * FROM Holidays;");
            while (rs.next())
                holidays.add(new HolidayPojo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("day"),
                        rs.getInt("month"),
                        rs.getInt("len")
                ));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return holidays;
    }
    public boolean add(HolidayPojo holiday){
        try {
            statement.executeUpdate(String.format("INSERT INTO Holidays(name,day,month,len)"
                    +"VALUES ('%s',%d,%d,%d);", holiday.name,holiday.day,holiday.month,holiday.length));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update(HolidayPojo holiday){
        try {
            statement.executeUpdate(String.format("UPDATE Holidays SET name = '%s', day = %d," +
                    "month = %d, len = %d WHERE Holidays.id = %d",
                    holiday.name,holiday.day,holiday.month,holiday.length,holiday.id));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(HolidayPojo holiday){
        try {
            statement.executeUpdate("DELETE FROM Holidays WHERE Holidays.id = " + holiday.id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
