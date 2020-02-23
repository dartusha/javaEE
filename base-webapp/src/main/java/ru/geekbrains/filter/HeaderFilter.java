package ru.geekbrains.filter;

import ru.geekbrains.servlet.Common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class HeaderFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
      //  response.getWriter().println("<h1>test filter</h1>");
    //    Common.addMenu(response);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
