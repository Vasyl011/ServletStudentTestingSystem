package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.OptionDao;
import ua.stasyk.servletproject.dao.QuestionDao;
import ua.stasyk.servletproject.dao.TestDao;
import ua.stasyk.servletproject.models.Option;
import ua.stasyk.servletproject.models.Question;
import ua.stasyk.servletproject.models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCQuestionDao implements QuestionDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCQuestionDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    @Override
    public Optional<Question> findById(Integer id) {
        try(Connection connection= connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUESTION_FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer questionId= resultSet.getInt("question_id");
                String questionName=resultSet.getString("question_text");
                return Optional.of(new Question(questionId,questionName));
            }
            return Optional.empty();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Question question) {
        try(Connection connection=connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUESTION_CREATE)){
            preparedStatement.setString(1,question.getQuestionText());
            preparedStatement.setInt(2,question.getTest().getTestId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Question model) {

    }

    @Override
    public void delete(Integer id) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUESTION_DELETE)) {
            OptionDao optionDao = DaoFactory.getInstance().createOptionDao();
            optionDao.deleteByQuestionId(id);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Question> findAll() {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUESTION_FIND_ALL)){
            List<Question> questions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer questionId = resultSet.getInt("question_id");
                String questionText= resultSet.getString("question_text");

                Integer testId = resultSet.getInt("test_id");
                TestDao testDao = DaoFactory.getInstance().createTestDao();
                Test test = testDao.findById(testId).get();


                OptionDao optionDao = DaoFactory.getInstance().createOptionDao();
                List<Option> options = optionDao.findAllByQuestionId(questionId);

                Question question = new Question(questionId,questionText,test,options);
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public Optional<Question> findByQuestionName(String questionName) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUESTION_FIND_BY_QUESTION_NAME)){
            preparedStatement.setString(1, questionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer questionId = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_text");

                return Optional.of(new Question(questionId,questionText));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Question> findAllByTestId(Integer testId) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUESTION_FIND_ALL_BY_TEST_ID)){
            preparedStatement.setInt(1, testId);
            List<Question> questions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer questionId = resultSet.getInt("question_id");
                String questionText= resultSet.getString("question_text");


                OptionDao optionDao = DaoFactory.getInstance().createOptionDao();
                List<Option> options = optionDao.findAllByQuestionId(questionId);

                Question question = new Question(questionId,questionText,options);
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
