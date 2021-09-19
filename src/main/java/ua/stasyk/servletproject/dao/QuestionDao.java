package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.models.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao extends CrudDao<Question>{
    Optional<Question> findByQuestionName(String questionName);
    List<Question> findAllByTestId(Integer testId);

}
