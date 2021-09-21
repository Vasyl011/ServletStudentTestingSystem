package ua.stasyk.servletproject.services;


import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.TestDao;
import ua.stasyk.servletproject.models.Test;

import java.util.List;


public class TestService {
    private TestDao testDao = DaoFactory.getInstance().createTestDao();

    public void create(String sub_name,String complexity,Integer duration){
        Test test = new Test();
        test.setSubjectName(sub_name);
        test.setComplexity(complexity);
        test.setDuration(duration);
        testDao.save(test);
    }

    public void delete(Integer testID){
        testDao.delete(testID);
    }

    public void edit (Integer testId, String subjectName,String complexity,Integer duration){
    Test test = new Test();
    test.setTestId(testId);
    test.setSubjectName(subjectName);
    test.setComplexity(complexity);
    test.setDuration(duration);
    testDao.update(test);
    }


    public List<Test> showTestList(){
        List<Test> tests = testDao.findAll();
        return tests;
    }

    public List<Test> showSortedTestListByName(){
        List<Test> tests = testDao.sortedByName();
        return tests;
    }

    public List<Test> showSortedTestListByComplexity(){
        List<Test> tests = testDao.sortedByComplexity();
        return tests;
    }

}
