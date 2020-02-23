package ru.geekbrains.persist;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderRepository {
    private final Connection conn;

    public OrderRepository(Connection conn) throws SQLException {
        this.conn=conn;
        createTablesIfNotExists(conn);
    }

    private void createTablesIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt=conn.createStatement()){
            stmt.execute("create table if not exists `orders`(" +
                    "`id` int auto_increment primary key," +
                    "`name` varchar(250)," +
                    "`phone` varchar(100)," +
                    "`email` varchar(100)," +
                    "`address` varchar(2000)," +
                    "`create_date` date," +
                    "`delivery_date` date," +
                    "`comments` varchar(2000)," +
                    "`amount` decimal(19,2)," +
                    "`status` int" +
                    ");");
        }

        try (Statement stmt=conn.createStatement()){
            stmt.execute("create table if not exists `orders_details`(" +
                    "`order_id` int," +
                    "`product_id` int(11)," +
                    "`cnt` int(11)," +
                    "`amount` decimal(19,2));");
        }

        try (Statement stmt=conn.createStatement()){
            stmt.execute("create table if not exists `dictionary`(" +
                    "`dict` varchar(250)," +
                    "`code` int(11)," +
                    "`term` varchar(2000)" +
                    " );");
        }
    }

    public void updateAmount(Long orderId)throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update `orders` set `amount`=(select sum(`amount`) from `orders_details` where `order_id`=?)   where `id`=?;"
        )) {
            stmt.setLong(1,orderId);
            stmt.setLong(2,orderId);
            stmt.execute();
        }
    }

    public void insert(Order order) throws SQLException {
        long orderId=0;
        try(PreparedStatement stmt=conn.prepareStatement(
                "insert into `orders`(`id`,`name`,`phone`,`email`,`address`,`create_date`,`delivery_date`,`comments`,`amount`,`status`)" +
                        " values (?,?,?,?,?,sysdate(),?,?,?,?);"
        ,Statement.RETURN_GENERATED_KEYS))
            {
            order.setStatus(1);

            stmt.setLong(1,order.getId());
            stmt.setString(2,order.getName());
            stmt.setString(3,order.getPhone());
            stmt.setString(4,order.getEmail());
            stmt.setString(5,order.getAddress());
          //  stmt.setDate(6,Date.valueOf(order.getCreateDate()));
            stmt.setDate(6,java.sql.Date.valueOf(order.getDeliveryDate()));
            stmt.setString(7,order.getComments());
            stmt.setBigDecimal(8,order.getAmount());
            stmt.setInt(9,order.getStatus());
            stmt.execute();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId=generatedKeys.getLong(1);
                    }
                    else {
                        throw new SQLException("No ID obtained.");
                    }
            }

        }

        for(OrderDetails det : order.getOrderDetails()){
            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into `orders_details`(`order_id`,`product_id`,`cnt`,`amount`)" +
                            " values (?,?,?,?);"
            )) {
                stmt.setLong(1, orderId);
                stmt.setLong(2, det.getProductId());
                stmt.setLong(3, det.getCnt());
                stmt.setBigDecimal(4, det.getAmount());
                stmt.execute();
            }
            }

          updateAmount(orderId);
        }

    public List<OrderDetails> createFromCart(List<Cart> cartList)  {
        List<OrderDetails> ord=new ArrayList<>();
        for (Cart cart:cartList){
            ord.add(new OrderDetails(0,cart.getProductId(),cart.getAmount(),cart.getCnt()
                    ));
        }
        return ord;
    }

    public void updateOrderDetails(OrderDetails orderDetails) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update `orders_details` set `cnt`=?,`amount`=(select `price` from `products` where id=`orders_details`.`product_id`) *? " +
                        " where `order_id`=? and `product_id`=?;"
        )) {
            stmt.setLong(1, orderDetails.getCnt());
            stmt.setLong(2, orderDetails.getCnt());
            stmt.setLong(3, orderDetails.getOrderId());
            stmt.setLong(4, orderDetails.getProductId());
            stmt.execute();
        }

        updateAmount(orderDetails.getOrderId());
    }

    public void deleteOrderDetails(long orderId, long productId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from `orders_details` where `order_id`=? and `product_id`=?;"
        )) {
            stmt.setLong(1, orderId);
            stmt.setLong(2, productId);
            stmt.execute();
        }

        updateAmount(orderId);
    }

    public void updateOrder(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update `orders` set `name`=?,`phone`=?,`email`=?,`address`=?,`delivery_date`=?,`comments`=?   where `id`=?;"
        )) {
            stmt.setString(1,order.getName());
            stmt.setString(2,order.getPhone());
            stmt.setString(3,order.getEmail());
            stmt.setString(4,order.getAddress());
            stmt.setString(5,order.getDeliveryDate());
            stmt.setString(6,order.getComments());
            stmt.setLong(7,order.getId());
            stmt.execute();
        }
    }

    public void deleteOrder(long orderId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from `orders_details` where `order_id`=?;"
        )) {
            stmt.setLong(1,orderId);
            stmt.execute();
        }

        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from `orders` where `order_id`=?;"
        )) {
            stmt.setLong(1,orderId);
            stmt.execute();
        }
    }

    public void changeStatus(long orderId,int status) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update `orders` set `status`=?  where `order_id`=?;"
        )) {
            stmt.setInt(1,status);
            stmt.setLong(2,orderId);
            stmt.execute();
        }
    }

    public List<OrderDetails> findDetailById(long id)throws SQLException {
        List<OrderDetails> res=new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(
                "select `order_id`,`product_id`,`cnt`,`amount`, (select `name` from `products` where id=`orders_details`.`product_id`), (select `price` from `products` where id=`orders_details`.`product_id`) from `orders_details` where `order_id`=?"
        )) {
            stmt.setLong(1,id);
            ResultSet rs=stmt.executeQuery();
            int cnt=0;
            while(rs.next()){
                res.add(new OrderDetails(rs.getLong(1),
                        rs.getLong(2),
                        rs.getBigDecimal(4),
                        rs.getLong(3),
                        rs.getString(5),++cnt,
                        rs.getBigDecimal(6)
                        ));
            }


        }
        return res;
    }

    public Order findById(long id) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "select `id`,`name`,`phone`,`email`,`address`,`create_date`,`delivery_date`,`comments`,`amount`," +
                        "(select term from `dictionary` where `dict`=1 and `code`=`orders`.`status`)  from `orders` where `id`=?"
        )){
            stmt.setLong(1,id);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                return new Order(rs.getLong(1),rs.getString(2)
                        ,rs.getString(3),rs.getString(4)
                        ,rs.getString(5),rs.getString(6)
                        ,rs.getString(7)
                        ,rs.getString(8),rs.getBigDecimal(9),findDetailById(id),rs.getString(10));
            }
        }
        return new Order(-1L,"","","","","","","", new BigDecimal(0),null,"");
    }

    public List<Order> findAll() throws SQLException {
        List<Order> res=new ArrayList<>();
        try(PreparedStatement stmt=conn.prepareStatement(
                "select `id`,`name`,`phone`,`email`,`address`,`create_date`,`delivery_date`,`comments`,`amount`, " +
                        " (select term from `dictionary` where `dict`=1 and `code`=`orders`.`status`) from `orders` "
        )){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                res.add(new Order(rs.getLong(1),rs.getString(2)
                        ,rs.getString(3),rs.getString(4)
                        ,rs.getString(5),rs.getString(6)
                        ,rs.getString(7)
                        ,rs.getString(8),rs.getBigDecimal(9),findDetailById(rs.getLong(1)),rs.getString(10)));
            }
        }

        return res;
    }

}
