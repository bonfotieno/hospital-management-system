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
    public void contextInitialized(ServletContextEvent ctxe) {
        System.out.print("eHospital Application Starting up....initializing default attributes");
        ServletContext ctx = ctxe.getServletContext();
        ctx.setAttribute("applicationLabel", "Botien | Online Hospital Management System");
    }

    public void contextDestroyed(ServletContextEvent ctxe) {
    }
}
