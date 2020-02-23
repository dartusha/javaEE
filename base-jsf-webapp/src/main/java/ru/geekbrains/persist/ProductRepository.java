package ru.geekbrains.persist;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Local
public interface ProductRepository extends Serializable {

    void insert(Product product);

    void update(Product product);

    void delete(long id);

    Product findById(long id);

    List<Product> findAll();
}