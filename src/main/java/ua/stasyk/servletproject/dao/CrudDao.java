package ua.stasyk.servletproject.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao <T>{

    Optional<T> findById (Integer id);

    void save (T model);

    void update(T model);

    void delete (Integer id);

    List<T> findAll();
}
