package ua.stasyk.servletproject.services;

import ua.stasyk.servletproject.PasswordEncoder;
import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.RoleDao;
import ua.stasyk.servletproject.dao.UserDao;
import ua.stasyk.servletproject.models.Role;
import ua.stasyk.servletproject.models.User;

import java.util.List;
import java.util.Optional;

public class UserService{

   private Integer ROLE_USER_ID = 0;

   private UserDao userDao = DaoFactory.getInstance().createUserDao();
   private RoleDao roleDao = DaoFactory.getInstance().createRoleDao();

    public User create(String username, String password) {
       User user = new User();
       Optional<User>userFromBD =userDao.findByUsername(username);
       if(userFromBD.isPresent()){
           return null;
       }
       Role role = roleDao.findById(ROLE_USER_ID).get();
       user.setUsername(username);
       user.setPassword(PasswordEncoder.encode(password));
       user.setBlocked(false);
       user.setRole(role);
       userDao.save(user);
       return user;
    }

    public User login(String username,String password){
        if (userDao.checkUser(username, PasswordEncoder.encode(password))) {
            return userDao.findByUsername(username).get();
        }
        return null;
    }

    public List<User> showUserList(){
        List<User> users = userDao.findAll();
        return users;
    }

    public void blockedUser (Integer userId){
        User user = new User();
        user.setId(userId);
        user.setBlocked(true);
        userDao.update(user);
    }

    public void unblockedUser (Integer userId){
        User user = new User();
        user.setId(userId);
        user.setBlocked(false);
        userDao.update(user);
    }
}
