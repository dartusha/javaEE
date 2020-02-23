package ru.geekbrains.persist;

import ru.geekbrains.service.ProductServiceRs;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@WebService(endpointInterface = "ru.geekbrains.persist.ProductRepositoryWS", serviceName = "ProductService")
public class ProductRepositoryImpl implements ProductRepository, ProductRepositoryRemote, ProductRepositoryWS, ProductServiceRs {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @PostConstruct
    public void init() {
    }

    @TransactionAttribute
    @Override
    public void insert(Product product) {
        em.persist(product);
    }

    @TransactionAttribute
    @Override
    public void update(Product product) {
        em.merge(product);
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    @Override
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

}
