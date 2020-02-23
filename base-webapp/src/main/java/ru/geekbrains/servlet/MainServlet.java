package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;

public class MainServlet implements Servlet, Serializable {
    private transient ServletConfig config;

    private Logger logger= LoggerFactory.getLogger(MainServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("Main request");
        res.getWriter().println("<head><title>Main</title></head>");
        res.getWriter().println("<h1>Main</h1>");
    }

    @Override
    public String getServletInfo() {
        return "Main";
    }

    @Override
    public void destroy() {
        logger.info("Main servlet ready to destroy");
    }
}
