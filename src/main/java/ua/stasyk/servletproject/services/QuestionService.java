package ua.stasyk.servletproject.services;

import ua.stasyk.servletproject.dao.DaoFactory;
import ua.stasyk.servletproject.dao.QuestionDao;
import ua.stasyk.servletproject.dao.TestDao;
import ua.stasyk.servletproject.models.Question;
import ua.stasyk.servletproject.models.Test;

import java.util.List;

public class QuestionService {

    private QuestionDao questionDao= DaoFactory.getInstance().createQuestionDao();
    private TestDao testDao = DaoFactory.getInstance().createTestDao();

    public void create(String questionText,String subjectName){
        Test test = testDao.findBySubjectName(subjectName).get();

        Question question=new Question();
        question.setQuestionText(questionText);
        question.setTest(test);
        questionDao.save(question);
    }

    public void delete(Integer questionID){
        questionDao.delete(questionID);
    }

    public List<Question> showQuestionList(){
        List<Question> questions = questionDao.findAll();
        return questions;
    }

    public List<Question> showQuestionListByTestId(Integer testId){
        List<Question> questions = questionDao.findAllByTestId(testId);
        return questions;
    }
}
