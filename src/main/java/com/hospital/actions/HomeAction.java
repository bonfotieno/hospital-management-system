package com.hospital.actions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect("./department.jsp");
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect("./department.jsp");
    }
}
