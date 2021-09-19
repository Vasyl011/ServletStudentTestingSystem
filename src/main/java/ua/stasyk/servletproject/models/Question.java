package ua.stasyk.servletproject.models;

import java.util.List;

public class Question {

    private Integer questionId;
    private String questionText;
    private Test test;
    private List<Option> options;

    public Question(){
    }

    public Question(Integer questionId, String questionText) {
        this.questionId = questionId;
        this.questionText = questionText;
    }

    public Question(Integer questionId, String questionText, Test test) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.test=test;
    }

    public Question(Integer questionId, String questionText, Test test,List<Option> options) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.test=test;
        this.options=options;
    }

    public Question(Integer questionId, String questionText,List<Option> options) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options=options;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
