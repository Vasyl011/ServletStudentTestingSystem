package ua.stasyk.servletproject.models;

public class Option {
    private Integer optionId;
    private Boolean optionCorrect;
    private String optionText;
    private Question question;

    public Option(){

    }
    public Option(Integer optionId, String optionText, Boolean optionCorrect) {
        this.optionId = optionId;
        this.optionText = optionText;
        this.optionCorrect = optionCorrect;

    }

    public Option(String optionText,Boolean optionCorrect) {
        this.optionText = optionText;
        this.optionCorrect = optionCorrect;
    }

    public Option(Integer optionId,Boolean optionCorrect) {
        this.optionId = optionId;
        this.optionCorrect = optionCorrect;
    }



    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Boolean getOptionCorrect() {
        return optionCorrect;
    }

    public void setOptionCorrect(Boolean optionCorrect) {
        this.optionCorrect = optionCorrect;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
