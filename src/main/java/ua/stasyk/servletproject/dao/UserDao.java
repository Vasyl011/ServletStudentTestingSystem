package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.models.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User>{
    Optional<User> findByUsername(String username);
    boolean checkUser(String username, String password);
    User findByName(String name);
}
