package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.models.StudentTest;

import java.util.List;

public interface StudentTestDao extends CrudDao<StudentTest> {
    List<StudentTest> findAllByTestIdAndStudentId(Integer testId,Integer studentId);
    List<StudentTest> findAllByStudentId(Integer studentId);
    boolean checkTest(Integer testId,Integer studentId);
    void deleteByTestId (Integer testId);
}
