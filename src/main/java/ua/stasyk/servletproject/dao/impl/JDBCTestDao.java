package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.StudentTestDao;
import ua.stasyk.servletproject.dao.TestDao;
import ua.stasyk.servletproject.models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTestDao implements TestDao {

    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCTestDao(final ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder = connectionPoolHolder;
    }

    @Override
    public Optional<Test> findById(Integer id) {
        try(Connection connection= connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer testId= resultSet.getInt("test_id");
                String testName=resultSet.getString("subject_name");
                Integer duration = resultSet.getInt("duration");
                return Optional.of(new Test(testId,testName,duration));
            }
            return Optional.empty();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Test test) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_CREATE)) {
            preparedStatement.setString(1,test.getSubjectName());
            preparedStatement.setString(2,test.getComplexity());
            preparedStatement.setInt(3,test.getDuration());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Test test) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_UPDATE)) {
            preparedStatement.setString(1,test.getSubjectName());
            preparedStatement.setString(2,test.getComplexity());
            preparedStatement.setInt(3,test.getDuration());
            preparedStatement.setInt(4,test.getTestId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_DELETE)) {
            StudentTestDao studentTestDao = DaoFactory.getInstance().createStudentTestDao();
            studentTestDao.deleteByTestId(id);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Test> findAll() {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_FIND_ALL)){
            List<Test> tests = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer test_id = resultSet.getInt("test_id");
                String subjectName= resultSet.getString("subject_name");
                String complexity= resultSet.getString("complexity");
                Integer duration= resultSet.getInt("duration");
                Integer number_of_questions=resultSet.getInt("number_of_questions");

                Test test = new Test(test_id,subjectName,complexity,duration,number_of_questions);
                tests.add(test);
            }
            return tests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Test> findBySubjectName(String subjectName) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_FIND_BY_SUBJECTNAME)){
            preparedStatement.setString(1, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer test_id = resultSet.getInt("test_id");
                String subName = resultSet.getString("subject_name");
                String complexity= resultSet.getString("complexity");
                Integer duration = resultSet.getInt("duration");
                Integer number_of_questions= resultSet.getInt("number_of_questions");
                return Optional.of(new Test(test_id,subName,complexity,duration,number_of_questions));
            }
         return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Test> sortedByName() {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_SORTED_BY_NAME)){
            List<Test> tests = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer test_id = resultSet.getInt("test_id");
                String subjectName= resultSet.getString("subject_name");
                String complexity= resultSet.getString("complexity");
                Integer duration= resultSet.getInt("duration");
                Integer number_of_questions=resultSet.getInt("number_of_questions");

                Test test = new Test(test_id,subjectName,complexity,duration,number_of_questions);
                tests.add(test);
            }
            return tests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Test> sortedByComplexity() {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.TEST_SORTED_BY_COMPLEXITY)){
            List<Test> tests = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer test_id = resultSet.getInt("test_id");
                String subjectName= resultSet.getString("subject_name");
                String complexity= resultSet.getString("complexity");
                Integer duration= resultSet.getInt("duration");
                Integer number_of_questions=resultSet.getInt("number_of_questions");

                Test test = new Test(test_id,subjectName,complexity,duration,number_of_questions);
                tests.add(test);
            }
            return tests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
