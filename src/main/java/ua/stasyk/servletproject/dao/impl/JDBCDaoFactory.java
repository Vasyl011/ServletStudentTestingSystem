package ua.stasyk.servletproject.dao.impl;

import ua.stasyk.servletproject.dao.*;

public class JDBCDaoFactory extends DaoFactory {
    private final ConnectionPoolHolder connectionPoolHolder;

    public JDBCDaoFactory(ConnectionPoolHolder connectionPoolHolder) {
        this.connectionPoolHolder=connectionPoolHolder;
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUsersDao(connectionPoolHolder);
    }

    @Override
    public RoleDao createRoleDao() {
        return new JDBCRoleDao(connectionPoolHolder);
    }

    @Override
    public TestDao createTestDao() {
        return new JDBCTestDao(connectionPoolHolder);
    }

    @Override
    public QuestionDao createQuestionDao() {
        return new JDBCQuestionDao(connectionPoolHolder);
    }

    @Override
    public OptionDao createOptionDao() {
        return new JDBCOptionDao(connectionPoolHolder);
    }

    @Override
    public StudentTestDao createStudentTestDao() {
        return new JDBCStudentTestDao(connectionPoolHolder);
    }
}
