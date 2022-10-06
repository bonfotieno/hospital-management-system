package com.hospital.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent ctxe) {
        System.out.print("eHospital Application Starting up....initializing default attributes");
        ServletContext ctx = ctxe.getServletContext();
        ctx.setAttribute("applicationLabel", "Botien | Online Hospital Management System");
    }

    public void contextDestroyed(ServletContextEvent ctxe) {
        System.out.print("eHospital Application stopped.");
    }
}
