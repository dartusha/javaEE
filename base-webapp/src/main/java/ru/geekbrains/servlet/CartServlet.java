package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Cart;
import ru.geekbrains.persist.CartRepository;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(CartServlet.class);

    @Override
    public void init() throws ServletException {
        this.cartRepository = (CartRepository) getServletContext().getAttribute("cartRepository");
        this.productRepository= (ProductRepository) getServletContext().getAttribute("productRepository");
        if (cartRepository == null) {
            throw new ServletException("CartRepository not created");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        String session = req.getSession().getId();
        if (command != null) {
            switch (command) {
                case "BUY": {
                    Long productId= Long.parseLong(req.getParameter("id"));
                    //req.setAttribute("cart", new Cart());
                    Cart cart=new Cart();
                    cart.setSessionID(session);
                    cart.setProductId( productId);
                    cart.setCnt(1L);
                    try {
                        cart.setAmount(productRepository.
                                findById(productId).getPrice()
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        cartRepository.insert(cart);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("product");
                    return;
                }
                case "TRUNCATE": {
                    try {
                        cartRepository.deleteAll(session);
                        resp.sendRedirect("cart");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return;
                }
                case "PLUS": {
                    Long productId= Long.parseLong(req.getParameter("id"));
                    try {
                        cartRepository.plus(session,productId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("cart");
                    return;
                }
                case "MINUS": {
                    Long productId= Long.parseLong(req.getParameter("id"));
                    try {
                        cartRepository.minus(session,productId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("cart");
                    return;
                }
                case "DELETE": {
                    Long productId= Long.parseLong(req.getParameter("id"));
                    try {
                        cartRepository.delete(session,productId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("cart");
                    return;
                }
                // getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
            }
        }
        try {
            req.setAttribute("carts", cartRepository.findAll(session));
            System.out.println(session);
            getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error("",e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
