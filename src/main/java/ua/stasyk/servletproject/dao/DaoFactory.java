package ua.stasyk.servletproject.dao;

import ua.stasyk.servletproject.dao.impl.ConnectionPoolHolder;
import ua.stasyk.servletproject.dao.impl.JDBCDaoFactory;


public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract RoleDao createRoleDao();

    public abstract TestDao createTestDao();

    public abstract QuestionDao createQuestionDao();

    public abstract OptionDao createOptionDao();

    public abstract StudentTestDao createStudentTestDao();

    public static DaoFactory getInstance(){
        if(daoFactory==null){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory=new JDBCDaoFactory(ConnectionPoolHolder.poolHolder());
                }
            }
        }
        return daoFactory;
    }
}
