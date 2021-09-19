package ua.stasyk.servletproject.services;

import ua.stasyk.servletproject.dao.*;
import ua.stasyk.servletproject.models.Option;
import ua.stasyk.servletproject.models.StudentTest;
import ua.stasyk.servletproject.models.Test;
import ua.stasyk.servletproject.models.User;

import java.time.LocalDateTime;
import java.util.List;


public class TakeTestService {

    private StudentTestDao studentTestDao = DaoFactory.getInstance().createStudentTestDao();
    private UserDao userDao = DaoFactory.getInstance().createUserDao();
    private TestDao testDao = DaoFactory.getInstance().createTestDao();
    private OptionDao optionDao = DaoFactory.getInstance().createOptionDao();

    private Float count=0.0f;

    public void create (Integer testId,String username){
        StudentTest studentTest = new StudentTest();
        Test test = testDao.findById(testId).get();
        studentTest.setTest(test);

        User user =userDao.findByName(username);
        studentTest.setStudent(user);
        studentTest.setStartTestTime(LocalDateTime.now());
        studentTest.setEndTestTime(LocalDateTime.now().plusMinutes(test.getDuration()));
        studentTestDao.save(studentTest);
    }

    public List<StudentTest> showStudentTestList(){
        List<StudentTest> studentTests = studentTestDao.findAll();
        return studentTests;
    }

    public List<StudentTest> showStudentTestListByTestId(Integer testId){
        List<StudentTest> studentTests = studentTestDao.findAllByTestId(testId);
        return studentTests;
    }

    public Float testResult(Integer answerId,Integer numberOfQuestions){
        Option option = optionDao.findById(answerId).get();
        if(option.getOptionCorrect()==true){
            count++;
        }
        Float result =((count/numberOfQuestions)*100);
        return result;
    }

    public void edit (Float result,Integer studentTestId){
        StudentTest studentTest = studentTestDao.findById(studentTestId).get();
        studentTest.setResult(result);
        studentTestDao.update(studentTest);
    }
}
