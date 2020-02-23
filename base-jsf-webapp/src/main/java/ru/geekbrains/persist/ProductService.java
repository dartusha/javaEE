package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductService {

    private static final Logger logger= LoggerFactory.getLogger(ProductService.class);

    @EJB
    private ProductRepository productRepository;

    @TransactionAttribute
    public void insert(ProductRepr product){
        productRepository.insert(new Product(product));
    }

    @TransactionAttribute
    public void delete(int id){
        productRepository.delete(id);
    }

    @TransactionAttribute
    public ProductRepr findById(long id) throws SQLException {
        return new ProductRepr(productRepository.findById(id));
    }

    @TransactionAttribute
    public boolean existsById(long id) throws SQLException {
        return productRepository.findById(id)!=null;
    }

    @TransactionAttribute
    public List<ProductRepr> getAllProducts() throws SQLException {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }


}
