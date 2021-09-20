package ua.stasyk.servletproject.services;


import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.OptionDao;
import ua.stasyk.servletproject.dao.QuestionDao;
import ua.stasyk.servletproject.models.Option;
import ua.stasyk.servletproject.models.Question;

public class OptionService {

    private OptionDao optionDao = DaoFactory.getInstance().createOptionDao();
    private QuestionDao questionDao = DaoFactory.getInstance().createQuestionDao();


    public void create(String optionText,Boolean optionCorrect,String questionName){
        Question question = questionDao.findByQuestionName(questionName).get();

        Option option = new Option();
        option.setOptionText(optionText);
        option.setOptionCorrect(optionCorrect);
        option.setQuestion(question);
        optionDao.save(option);
    }

}
