package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.models.Test;

import java.util.List;
import java.util.Optional;

public interface TestDao extends CrudDao<Test> {
    Optional<Test> findBySubjectName(String subjectName);
    List<Test> sortedByName();
    List<Test> sortedByComplexity();
}
