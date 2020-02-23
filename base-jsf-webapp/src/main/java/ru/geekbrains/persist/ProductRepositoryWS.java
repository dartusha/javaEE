package ru.geekbrains.persist;

import ru.geekbrains.persist.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductRepositoryWS {
    @WebMethod
    List<Product> findAll();
    @WebMethod
    void insert(Product product);
    @WebMethod
    void update(Product product);
    @WebMethod
    void delete(long id);
    @WebMethod
    Product findById(long id);
}
