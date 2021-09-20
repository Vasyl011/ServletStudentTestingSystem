package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.UserDao;
import ua.stasyk.servletproject.models.Role;
import ua.stasyk.servletproject.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUsersDao implements UserDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCUsersDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }


    @Override
    public Optional<User> findById(Integer id) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueries.USER_FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                String username=resultSet.getString("username");
                String password=resultSet.getString("password");

                return Optional.of(new User(id,username,password));
            }
            return Optional.empty();
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User user) {
        try(Connection connection=connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_CREATE)){
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setBoolean(3,user.isBlocked());
            preparedStatement.setInt(4,user.getRole().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User user) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_UPDATE)) {
            preparedStatement.setBoolean(1,user.isBlocked());
            preparedStatement.setInt(2,user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_FIND_ALL)){
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer userId = resultSet.getInt("usr_id");
                String username= resultSet.getString("username");
                Boolean blocked= resultSet.getBoolean("blocked");


                User user = new User(userId,username,blocked);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("usr_id");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                Integer roleId = resultSet.getInt("role_id");
                String roleName = resultSet.getString("role");
                Role role = new Role(roleId, roleName);
                Boolean blocked = resultSet.getBoolean("blocked");
                return Optional.of(new User(id, username, password, role,blocked));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean checkUser(String username, String password) {
        boolean exist = false;
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(SQLQueries.USER_CHECK)){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist=resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }

    @Override
    public User findByName(String name) {
        try (Connection connection = connectionPoolHolder.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.USER_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("usr_id");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                Boolean blocked= resultSet.getBoolean("blocked");

                return(new User(id, username, password,blocked));
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}


