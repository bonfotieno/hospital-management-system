package com.hospital.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    public static int CurrentLoggedInUsers = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        CurrentLoggedInUsers+=1;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        CurrentLoggedInUsers-=1;
    }
}
