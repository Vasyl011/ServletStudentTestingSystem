package ua.stasyk.servletproject.dao;


import ua.stasyk.servletproject.models.Role;

public interface RoleDao extends CrudDao<Role> {
    Role findByRole(String role);
}
