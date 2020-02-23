package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private ProductRepository productRepository;
    private  Logger logger=LoggerFactory.getLogger(ProductServlet.class);

    @Override
    public void init() throws ServletException {
        this.productRepository= (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("ProductRepository not created");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        if (command != null) {
            switch (command) {
                case "NEW": {
                    req.setAttribute("product", new Product());
                    getServletContext().getRequestDispatcher("/product.jsp")
                            .forward(req, resp);
                    return;
                }
                case "EDIT": {
                    try {
                        req.setAttribute("product", productRepository.findById(Long.parseLong(req.getParameter("id"))));
                        getServletContext().getRequestDispatcher("/product.jsp")
                                .forward(req, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                case "DELETE": {
                    try {
                        productRepository.delete(Long.parseLong(req.getParameter("id")));
                        req.setAttribute("products", productRepository.findAll());
                        resp.sendRedirect("product");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }


        try {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error("",e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setDescription(req.getParameter("description"));
        product.setPrice(new BigDecimal(req.getParameter("price")));
        if (id.equals("-1")) {
            try {
                productRepository.insert(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                product.setId(Long.parseLong(id));
                productRepository.update(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("product");
    }
}
