package ua.stasyk.servletproject.models;

import java.time.LocalDateTime;

public class StudentTest {

    private Integer studentTestId;
    private User student;
    private Test test;
    private Float result;
    private LocalDateTime startTestTime;
    private LocalDateTime endTestTime;
    private LocalDateTime actualEndTestTime;

    public StudentTest(){}

    public StudentTest(Integer studentTestId, User student, Test test, Float result, LocalDateTime startTestTime, LocalDateTime endTestTime,LocalDateTime actualEndTestTime) {
        this.studentTestId = studentTestId;
        this.student = student;
        this.test = test;
        this.result = result;
        this.startTestTime = startTestTime;
        this.endTestTime = endTestTime;
        this.actualEndTestTime = actualEndTestTime;
    }

    public StudentTest(Integer studentTestId, LocalDateTime startTestTime, LocalDateTime endTestTime) {
        this.studentTestId = studentTestId;
        this.startTestTime = startTestTime;
        this.endTestTime = endTestTime;

    }

    public Integer getStudentTestId() {
        return studentTestId;
    }

    public void setStudentTestId(Integer studentTestId) {
        this.studentTestId = studentTestId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public LocalDateTime getStartTestTime() {
        return startTestTime;
    }

    public void setStartTestTime(LocalDateTime startTestTime) {
        this.startTestTime = startTestTime;
    }

    public LocalDateTime getEndTestTime() {
        return endTestTime;
    }

    public void setEndTestTime(LocalDateTime endTestTime) {
        this.endTestTime = endTestTime;
    }

    public LocalDateTime getActualEndTestTime() {
        return actualEndTestTime;
    }

    public void setActualEndTestTime(LocalDateTime actualEndTestTime) {
        this.actualEndTestTime = actualEndTestTime;
    }
}
