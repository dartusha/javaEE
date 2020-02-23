package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    @Override
    public void init() throws ServletException {
        this.cartRepository = (CartRepository) getServletContext().getAttribute("cartRepository");
        this.productRepository= (ProductRepository) getServletContext().getAttribute("productRepository");
        this.orderRepository= (OrderRepository) getServletContext().getAttribute("orderRepository");
        if (orderRepository == null) {
            throw new ServletException("orderRepository not created");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");

        if (command != null) {
            switch (command) {
                case "NEW": {
                    req.setAttribute("order", new Order());
                    getServletContext().getRequestDispatcher("/order.jsp")
                            .forward(req, resp);
                    return;
                }
                case "EDIT": {
                    try {
                        req.setAttribute("order", orderRepository.findById(Long.parseLong(req.getParameter("id"))));
                        getServletContext().getRequestDispatcher("/order.jsp")
                                .forward(req, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                case "DELETE": {
                    try {
                        orderRepository.deleteOrder(Long.parseLong(req.getParameter("id")));
                        req.setAttribute("orders", orderRepository.findAll());
                        resp.sendRedirect("order");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                case "DELETEDT": {
                    try {
                        orderRepository.deleteOrderDetails(Long.parseLong(req.getParameter("detailId")),
                                Long.parseLong(req.getParameter("productId")));
                        req.setAttribute("orders", orderRepository.findAll());
                        resp.sendRedirect("order");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }

        try {
            req.setAttribute("orders", orderRepository.findAll());
            getServletContext().getRequestDispatcher("/orders.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error("",e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (req.getParameter("detailId")!=null){
            OrderDetails orderDetails=new OrderDetails();
            orderDetails.setOrderId(Long.parseLong(req.getParameter("detailId")));
            orderDetails.setProductId(Long.parseLong(req.getParameter("detailProductId")));
            orderDetails.setAmount(new BigDecimal(req.getParameter("detailAmount")));
            orderDetails.setCnt(Long.parseLong(req.getParameter("cnt")));
            try {
                orderRepository.updateOrderDetails(orderDetails);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("order");
        }
        else{
            Order order = new Order();
            order.setName(req.getParameter("name"));
            order.setPhone(req.getParameter("phone"));
            order.setEmail(req.getParameter("email"));
            order.setDeliveryDate(req.getParameter("deliveryDate"));
            order.setAddress(req.getParameter("address"));
            order.setComments(req.getParameter("comments"));
            String session = req.getSession().getId();


            if (id.equals("0")) {
                try {
                    List<Cart> carts = cartRepository.findAll(session);
                    order.setOrderDetails(orderRepository.createFromCart(carts));
                    orderRepository.insert(order);
                    cartRepository.deleteAll(session);
                    resp.sendRedirect("cart");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    order.setId(Long.parseLong(id));
                    orderRepository.updateOrder(order);
                    resp.sendRedirect("order");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }
    }
}