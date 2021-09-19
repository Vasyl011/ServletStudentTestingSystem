package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.models.Option;

import java.util.List;

public interface OptionDao extends CrudDao<Option> {
    List<Option> findAllByQuestionId(Integer questionId);
    void deleteByQuestionId (Integer questionId);
}
