package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name="DBServlet", urlPatterns = "/dbservlet/*")
public class DBServlet extends HttpServlet {
    private Connection connection;
    private Logger logger = LoggerFactory.getLogger(DBServlet.class);


    @Override
    public void init() throws ServletException {
        connection= (Connection) getServletContext().getAttribute("jdbcConnection");
        System.out.println("connect:"+connection);
        if (connection == null) {
            throw new ServletException("No JDBC connection in Servlet Context");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // getServletContext().getRequestDispatcher("/WEB-INF/web.xml").include(req,resp);

        logger.info("Get all tables");
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES;");
            while (rs.next()) {
                String tableName = rs.getString(0);
                resp.getWriter().println("<p> " + tableName + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }
}
