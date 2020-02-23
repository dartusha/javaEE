package ru.geekbrains.controller;

import ru.geekbrains.persist.*;

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
    public class UserController implements Serializable {
        @Inject
        private UserRepository userRepository;

        @EJB
        private UserService userService;

        private User user;

        public User  getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Transactional
        public String createUser(){
            this.user=new User();
            return "/product.xhtml?faces-redirect=true";
        }

        public List<User> getAllUsers() throws SQLException {
            return userRepository.findAll();
        }
        public String editUser(User user){
            this.user=user;
            return "/product.xhtml?faces-redirect=true";
        }
        @Transactional
        public void deleteUser(User user) throws SQLException {
            userRepository.delete(user.getId());
        }
        @Transactional
        public String saveUser() throws SQLException {
            userRepository.insert(user);
            return "/products.xhtml?faces-redirect=true";
        }

        public String returnUser() {
            return "/products.xhtml?faces-redirect=true";
        }

    }


