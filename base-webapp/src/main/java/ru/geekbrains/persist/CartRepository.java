package ru.geekbrains.persist;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private final Connection conn;

    public CartRepository(Connection conn) throws SQLException {
        this.conn=conn;
        createTableIfNotExists(conn);
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt=conn.createStatement()){
            stmt.execute("create table if not exists `cart`(" +
                    "`session_id` varchar(25)," +
                    "`product_id` int(11)," +
                    "`cnt` int(11)," +
                    "`amount` decimal(19,2));");
        }
    }

    public void insert(Cart cart) throws SQLException {
        int res=0;
        try(PreparedStatement stmt=conn.prepareStatement(
                "update cart set `cnt`=`cnt`+?, `amount`=`amount`+?  where `session_id`=? and `product_id`=?;"
        )){
            stmt.setLong(1,cart.getCnt());
            stmt.setBigDecimal(2, cart.getAmount());
            stmt.setString(3,cart.getSessionID());
            stmt.setLong(4,cart.getProductId());
            res=stmt.executeUpdate();
        }

        if (res==0) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into cart(`session_id`,`product_id`,`cnt`,`amount`)" +
                            " values (?,?,?,?);"
            )) {
                stmt.setString(1, cart.getSessionID());
                stmt.setLong(2, cart.getProductId());
                stmt.setLong(3, cart.getCnt());
                stmt.setBigDecimal(4, cart.getAmount());
                stmt.execute();
            }
        }
    }

    public void update(Cart cart) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "update cart set `cnt`=? where `session_id`=?;"
        )){
            stmt.setLong(1,cart.getCnt());
            stmt.execute();
        }
    }

    public void plus(String sessionId, long productId) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "update cart set `cnt`=`cnt`+1, `amount`= `amount`+"+
                "(select `price` from `products` where `id`=?) "+
                        "where `session_id`=? and `product_id`=?;"
        )){
            stmt.setLong(1, productId);
            stmt.setString(2, sessionId);
            stmt.setLong(3, productId);
            stmt.execute();
        }
    }

    public void minus(String sessionId, long productId) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "update cart set `cnt`=`cnt`-1, `amount`= `amount`-"+
                        "(select `price` from `products` where `id`=?) "+
                        "where `session_id`=? and `product_id`=? and `cnt`>1;"
        )){
            stmt.setLong(1, productId);
            stmt.setString(2, sessionId);
            stmt.setLong(3, productId);
            stmt.execute();
        }
    }

    public void deleteAll(String sessionId) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "delete from cart where `session_id`=?;"
        )){
            stmt.setString(1,sessionId);
            stmt.execute();
        }
    }

    public void delete(String sessionId, long productId) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "delete from cart where `session_id`=? and `product_id`=?;"
        )){
            stmt.setString(1,sessionId);
            stmt.setLong(2,productId);
            stmt.execute();
        }
    }

    public Cart findById(String sessionId) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "select `session_id`,`product_id`,`cnt`,`amount` from `cart` where `session_id`=?"
        )){
            stmt.setString(1,sessionId);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                return new Cart(rs.getString(1),rs.getLong(2),
                        rs.getBigDecimal(4),rs.getLong(3));
            }

        }
        return new Cart("0",0,new BigDecimal(0),0);
    }

    public List<Cart> findAll(String sessionId) throws SQLException {
        List<Cart> res=new ArrayList<>();
        int rowid=0;
        try(PreparedStatement stmt=conn.prepareStatement(
                "select `session_id`,`product_id`,`cnt`,`amount`,`products`.`name` from `cart`" +
                        " join `products` on `cart`.`product_id`=`products`.`id`" +
                        " where `session_id`=?"
        )){
            stmt.setString(1,sessionId);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                res.add(new Cart(rs.getString(1),rs.getLong(2),
                        rs.getBigDecimal(4),rs.getLong(3),
                        rs.getString(5)
                        ,++rowid
                ));
            }
        }

        return res;
    }

    public BigDecimal getTotal(String sessionId) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "select sum(amount) from `cart` where `session_id`=?"
        )){
            stmt.setString(1,sessionId);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                return
                        rs.getBigDecimal(1);
            }

        }
        return new BigDecimal(0);
    }

}
