package ru.geekbrains.controller;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {
    @Inject
    private ProductRepository productRepository;

    @EJB
    private ProductService productService;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Transactional
    public String createProduct(){
        this.product=new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProducts() throws SQLException {
            return productRepository.findAll();
    }
    public String editProduct(Product product){
        this.product=product;
        return "/product.xhtml?faces-redirect=true";
    }
    @Transactional
    public void deleteProduct(Product product) throws SQLException {
        productRepository.delete(product.getId());
    }
    @Transactional
    public String saveProduct() throws SQLException {
        productRepository.insert(product);
        return "/products.xhtml?faces-redirect=true";
    }

    public String returnProduct() {
        return "/products.xhtml?faces-redirect=true";
    }

}
