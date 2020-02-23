package ru.geekbrains.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.CartRepository;
import ru.geekbrains.persist.OrderRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppListener implements ServletContextListener {

    private Logger logger= LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        logger.info("Context initialization: " + context.getContextPath());

        String jdbcConnectionString = context.getInitParameter("jdbcConnectionString");
        String username = context.getInitParameter("dbUsername");
        String password = context.getInitParameter("dbPassword");

        if (isNotNullOrEmpty(jdbcConnectionString) || isNotNullOrEmpty(username)) {
            logger.error("Connection string and DB username must be specified");
            return;
        }

        try {
        Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);
         ProductRepository productRepository=new ProductRepository(conn);
         CartRepository cartRepository=new CartRepository(conn);
         OrderRepository orderRepository=new OrderRepository(conn);
         context.setAttribute("jdbcConnection", conn);
         context.setAttribute("productRepository",productRepository);
         context.setAttribute("cartRepository",cartRepository);
         context.setAttribute("orderRepository",orderRepository);

         if (productRepository.findAll().size()==0){
             productRepository.insert(new Product(-1L,"test","test",new BigDecimal(10)));
         }
    } catch (SQLException e) {
        logger.error("", e);
    }


}


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Connection conn = (Connection) context.getAttribute("jdbcConnection");
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error("", e);
        }
    }

    private boolean isNotNullOrEmpty(String str) {
        return str != null && str.isEmpty();
    }


}
