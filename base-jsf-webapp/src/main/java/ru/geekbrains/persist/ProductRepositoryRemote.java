package ru.geekbrains.persist;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface ProductRepositoryRemote {
    List<Product> findAll();
    void insert(Product product);
    void update(Product product);
    void delete(long id);
    Product findById(long id);
}
