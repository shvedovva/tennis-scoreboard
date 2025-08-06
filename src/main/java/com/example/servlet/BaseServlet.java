package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspPath) throws ServletException, IOException {
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    protected void redirectTo(HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(path);
    }

    protected String getStringParameter(HttpServletRequest request, String paramName){
        String value = request.getParameter(paramName);
        return value != null ? value.trim() : null;
    }

    protected Integer getIntParameter(HttpServletRequest request, String paramName){
        String value = request.getParameter(paramName);
        if (value == null || value.trim().isEmpty()){
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e){
            return null;
        }
    }
}
