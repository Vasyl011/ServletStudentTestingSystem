package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.RoleDao;
import ua.stasyk.servletproject.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCRoleDao implements RoleDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCRoleDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    @Override
    public Optional<Role> findById(Integer id) {
        try(Connection connection= connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ROLE_FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer roleId= resultSet.getInt("id");
                String roleName=resultSet.getString("role");
                return Optional.of(new Role(roleId,roleName));
            }
            return Optional.empty();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Role model) {

    }

    @Override
    public void update(Role model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findByRole(String role) {
        return null;
    }
}
