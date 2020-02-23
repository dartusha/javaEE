package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService {

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;

    @TransactionAttribute
    public void insert(UserRepr user){
        userRepository.insert(new User(user));
    }

    @TransactionAttribute
    public void delete(int id){
        userRepository.delete(id);
    }

    @TransactionAttribute
    public UserRepr findById(long id) throws SQLException {
        return new UserRepr(userRepository.findById(id));
    }

    @TransactionAttribute
    public boolean existsById(long id) throws SQLException {
        return userRepository.findById(id)!=null;
    }

    @TransactionAttribute
    public List<UserRepr> getAllProducts() throws SQLException {
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }


}