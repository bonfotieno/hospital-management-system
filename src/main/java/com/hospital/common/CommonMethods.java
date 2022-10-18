package com.hospital.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommonMethods {
    public static boolean IsSessionExpired(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) { //checks if the previous session expired
            session.invalidate();
            resp.sendRedirect("");
            return true;
        } else {
            return false;
        }
    }
}
