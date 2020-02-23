package ru.geekbrains.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final Connection conn;

    public ProductRepository(Connection conn) throws SQLException {
        this.conn=conn;
        createTableIfNotExists(conn);
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt=conn.createStatement()){
            stmt.execute("create table if not exists `products`(" +
                    "`id` int auto_increment primary key," +
                    "`name` varchar(25)," +
                    "`description` varchar(250)," +
                    "`price` decimal(19,2));");
        }
    }

    public void insert(Product product) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "insert into products(`name`,`description`,`price`)" +
                        " values (?,?,?);"
        )){
            stmt.setString(1,product.getName());
            stmt.setString(2,product.getDescription());
            stmt.setBigDecimal(3,product.getPrice());
            stmt.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "update products set `name`=?, `description`=?, `price`=? where `id`=?;"
        )){
            stmt.setString(1,product.getName());
            stmt.setString(2,product.getDescription());
            stmt.setBigDecimal(3,product.getPrice());
            stmt.setLong(4,product.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "delete from products where `id`=?;"
        )){
            stmt.setLong(1,id);
            stmt.execute();
        }
    }

    public Product findById(long id) throws SQLException {
        try(PreparedStatement stmt=conn.prepareStatement(
                "select `id`,`name`,`description`,`price` from `products` where `id`=?"
        )){
            stmt.setLong(1,id);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                return new Product(rs.getLong(1),rs.getString(2),
                        rs.getString(3),rs.getBigDecimal(4));
            }
        }
        return new Product(-1L,"","",null);
    }

    public List<Product> findAll() throws SQLException {
        List<Product> res=new ArrayList<>();
        try(PreparedStatement stmt=conn.prepareStatement(
                "select `id`,`name`,`description`,`price` from `products`"
        )){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                res.add(new Product(rs.getLong(1),rs.getString(2),
                        rs.getString(3),rs.getBigDecimal(4)));
            }
        }

        return res;
    }

}
