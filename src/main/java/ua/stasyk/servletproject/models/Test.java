package ua.stasyk.servletproject.models;

public class Test {

    private Integer testId;
    private String subjectName;
    private String complexity;
    private Integer duration;


    public Test(Integer testId,String subjectName,String complexity,Integer duration){
        this.testId=testId;
        this.subjectName=subjectName;
        this.complexity=complexity;
        this.duration=duration;
    }

    public Test(Integer testId , String subjectName){
        this.testId=testId;
        this.subjectName=subjectName;
    }

    public Test(Integer testId , String subjectName , Integer duration ){
        this.testId=testId;
        this.subjectName=subjectName;
        this.duration=duration;
    }

    public Test( String subjectName){
        this.subjectName=subjectName;
    }

    public Test() {
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
