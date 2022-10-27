package com.hospital.listeners;

import com.zaxxer.hikari.HikariDataSource;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Resource(lookup = "java:jboss/datasources/hospital_sys")
    DataSource dataSource;
    public void contextInitialized(ServletContextEvent ctxe) {
        System.out.print("eHospital Application Starting up....initializing default attributes");
        ServletContext ctx = ctxe.getServletContext();
        ctx.setAttribute("applicationLabel", "Botien | Online Hospital Management System");
        try {
            System.out.print("Establishing connections....");
            Connection connection = dataSource.getConnection();
            ctx.setAttribute("dbConnection", connection);
            System.out.print("Connection Established....");
        } catch (Exception ex) {
            System.out.println("Connection Not Established....: " + ex.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent ctxe) {
        try {
            ServletContext ctx = ctxe.getServletContext();
            Connection connection = (Connection) ctx.getAttribute("dbConnection");
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
