package ru.geekbrains.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class Common {

    public static void addMenu(ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<div>");
        res.getWriter().println("<ul>");
        res.getWriter().println("<li>" + "<a href=\"main\">" + "MAIN" + "</a>" + "</li>");
        res.getWriter().println("<li>" + "<a href=\"cart\">" + "CART" + "</a>" + "</li>");
        res.getWriter().println("<li>" + "<a href=\"catalog\">" + "CATALOG" + "</a>" + "</li>");
        res.getWriter().println("<li>" + "<a href=\"order\">" + "ORDER" + "</a>" + "</li>");
        res.getWriter().println("<li>" + "<a href=\"product\">" + "PRODUCT" + "</a>" + "</li>");
        res.getWriter().println("</ul>");
        res.getWriter().println("</div>");
    }
}
