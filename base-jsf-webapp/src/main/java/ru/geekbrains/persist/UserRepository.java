package ru.geekbrains.persist;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface UserRepository extends Serializable {

        void insert(User user);

        void update(User user);

        void delete(long id);

        User findById(long id);

        List<User> findAll();
}
