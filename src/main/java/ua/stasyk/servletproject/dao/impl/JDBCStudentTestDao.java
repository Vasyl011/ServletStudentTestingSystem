package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.StudentTestDao;
import ua.stasyk.servletproject.dao.TestDao;
import ua.stasyk.servletproject.dao.UserDao;
import ua.stasyk.servletproject.models.StudentTest;
import ua.stasyk.servletproject.models.Test;
import ua.stasyk.servletproject.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCStudentTestDao implements StudentTestDao {

private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCStudentTestDao(final ConnectionPoolHolder connectionPoolHolder) {
    this.connectionPoolHolder=connectionPoolHolder;
    }

    @Override
    public Optional<StudentTest> findById(Integer id) {
        try(Connection connection= connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.STUDENT_TEST_FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer studentTestsId = resultSet.getInt("student_tests_id");
                LocalDateTime starTest= (LocalDateTime) resultSet.getObject("start_test");
                LocalDateTime endTest= (LocalDateTime) resultSet.getObject("end_test");

                return Optional.of(new StudentTest(studentTestsId,starTest,endTest));
            }
            return Optional.empty();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(StudentTest studentTest) {
        try(Connection connection=connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.STUDENT_TEST_CREATE)){
            preparedStatement.setInt(1,studentTest.getTest().getTestId());
            preparedStatement.setInt(2,studentTest.getStudent().getId());
            preparedStatement.setObject(3,studentTest.getStartTestTime());
            preparedStatement.setObject(4,studentTest.getEndTestTime());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(StudentTest studentTest) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.STUDENT_TEST_UPDATE)) {
            preparedStatement.setObject(1,studentTest.getActualEndTestTime());
            preparedStatement.setFloat(2,studentTest.getResult());
            preparedStatement.setInt(3,studentTest.getStudentTestId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<StudentTest> findAll() {
        return null;
    }

    @Override
    public List<StudentTest> findAllByTestIdAndStudentId(Integer testId, Integer studentId) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.STUDENT_TEST_FIND_BY_TEST_ID_AND_STUDENT_ID)){
            preparedStatement.setInt(1, testId);
            preparedStatement.setInt(2,studentId);
            List<StudentTest> studentTests = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer studentTestsId = resultSet.getInt("student_tests_id");
                LocalDateTime starTest= (LocalDateTime) resultSet.getObject("start_test");
                LocalDateTime endTest= (LocalDateTime) resultSet.getObject("end_test");

                StudentTest studentTest = new StudentTest(studentTestsId,starTest,endTest);
                studentTests.add(studentTest);
            }
            return studentTests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<StudentTest> findAllByStudentId(Integer userId) {
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.STUDENT_TEST_FIND_BY_STUDENT_ID)){
            preparedStatement.setInt(1, userId);
            List<StudentTest> studentTests = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer studentTestsId = resultSet.getInt("student_tests_id");

                Integer studentId = resultSet.getInt("student_id");
                UserDao userDao = DaoFactory.getInstance().createUserDao();
                User user = userDao.findById(studentId).get();

                Integer testId = resultSet.getInt("test_id");
                TestDao testDao = DaoFactory.getInstance().createTestDao();
                Test test = testDao.findById(testId).get();

                Float result = resultSet.getFloat("result");
                LocalDateTime actualEndTestTime = (LocalDateTime) resultSet.getObject("actual_end_test_time");
                LocalDateTime starTest= (LocalDateTime) resultSet.getObject("start_test");
                LocalDateTime endTest= (LocalDateTime) resultSet.getObject("end_test");

                LocalDateTime testTime = actualEndTestTime.minusMinutes(starTest.getMinute());
                StudentTest studentTest = new StudentTest(studentTestsId,user,test,result,starTest,endTest,testTime);
                studentTests.add(studentTest);
            }
            return studentTests;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean checkTest(Integer testId,Integer studentId) {
        boolean exist = false;
        try(Connection connection = connectionPoolHolder.getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(SQLQueries.STUDENT_TEST_CHECK)){
            preparedStatement.setInt(1,testId);
            preparedStatement.setInt(2,studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist=resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }

    @Override
    public void deleteByTestId(Integer testId) {
        try (Connection connection=connectionPoolHolder.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(SQLQueries.STUDENT_TEST_DELETE_BY_TEST_ID)){
            preparedStatement.setInt(1,testId);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
