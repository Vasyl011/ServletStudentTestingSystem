package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.models.StudentTest;

import java.util.List;

public interface StudentTestDao extends CrudDao<StudentTest> {
    List<StudentTest> findAllByTestId(Integer testId);
}
