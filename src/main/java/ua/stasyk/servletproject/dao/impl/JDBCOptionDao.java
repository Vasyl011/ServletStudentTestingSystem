package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.OptionDao;
import ua.stasyk.servletproject.models.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOptionDao implements OptionDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCOptionDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    @Override
    public Optional<Option> findById(Integer id) {
        try(Connection connection= connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.OPTION_FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer optionId= resultSet.getInt("option_id");
                Boolean option_correct=resultSet.getBoolean("option_correct");
                return Optional.of(new Option(optionId,option_correct));
            }
            return Optional.empty();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Option option) {
        try(Connection connection=connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.OPTION_CREATE)){
            preparedStatement.setString(1,option.getOptionText());
            preparedStatement.setBoolean(2,option.getOptionCorrect());
            preparedStatement.setInt(3,option.getQuestion().getQuestionId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Option model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Option> findAll() {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.OPTION_FIND_ALL)){
            List<Option> options = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer optionId = resultSet.getInt("option_id");
                String optionText = resultSet.getString("option_text");
                Boolean optionCorrect = resultSet.getBoolean("option_correct");

                Option option = new Option(optionId,optionText,optionCorrect);
                options.add(option);
            }
            return options;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Option> findAllByQuestionId(Integer questionId) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.OPTION_FIND_ALL_BY_QUESTION_ID)){
            preparedStatement.setInt(1,questionId);
            List<Option> options = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer optionId = resultSet.getInt("option_id");
                String optionText = resultSet.getString("option_text");
                Boolean optionCorrect = resultSet.getBoolean("option_correct");

                Option option = new Option(optionId,optionText,optionCorrect);
                options.add(option);
            }
            return options;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteByQuestionId(Integer questionId) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.OPTION_DELETE_BY_QUESTION_ID)){
            preparedStatement.setInt(1, questionId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
