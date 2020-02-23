package ru.geekbrains.persist;

public class UserRepr {
        private long id;
        private String login;
        private String password;
        private String name;
        private String surname;

        public UserRepr(){

        }

        public UserRepr(User user) {
            this.id = user.getId();
            this.login = user.getLogin();
            this.password = user.getPassword();
            this.name = user.getName();
            this.surname = user.getSurname();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }


    }


