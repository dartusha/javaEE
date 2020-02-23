package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @PostConstruct
    public void init() {
    }

    @TransactionAttribute
    @Override
    public void insert(User user) {
        em.persist(user);
    }

    @TransactionAttribute
    @Override
    public void update(User user) {
        em.merge(user);
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        User user= em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

}
