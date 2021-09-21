package ua.stasyk.servletproject.dao.impl;

public class SQLQueries {

    //User
    //language=SQL
    public static final String USER_FIND_ALL = "SELECT * FROM usr WHERE role_id  = 0";
    //language=SQL
    public static final String USER_FIND_BY_ID= "SELECT *FROM usr WHERE usr_id = ?";
    //language=SQL
    public static final String USER_FIND_BY_USERNAME = "SELECT usr.*, role FROM usr LEFT JOIN roles ON usr.role_id = roles.id where usr.username = ?";
    //language=SQL
    public static final String USER_CREATE = "INSERT INTO usr(username,password,blocked,role_id) VALUES (?,?,?,?)";
    //language=SQL
    public static final String USER_CHECK = "SELECT * FROM usr WHERE username = ? AND password = ?";
    //language=SQL
    public static final String USER_FIND_BY_NAME = "SELECT * FROM usr WHERE username=?";
    //language=SQL
    public static final String USER_UPDATE = "UPDATE usr SET blocked = ?  WHERE usr_id = ?";


    //Role
    //language=SQL
    public static final String ROLE_FIND_BY_ID = "SELECT * FROM roles WHERE id = ?";

    //Test
    //language=SQL
    public static final String TEST_CREATE = "INSERT INTO tests (subject_name,complexity,duration) VALUES (?,?,?)";
    //language=SQL
    public static final String TEST_FIND_ALL = "SELECT * FROM tests";
    //language=SQL
    public static final String TEST_DELETE = "DELETE FROM tests WHERE test_id=?";
    //language=SQL
    public static final String TEST_UPDATE = " UPDATE tests SET subject_name = ? , complexity = ? , duration = ? WHERE test_id = ?";
    //language=SQL
    public static final String TEST_FIND_BY_SUBJECTNAME= "SELECT * FROM tests WHERE subject_name=?";
    //language=SQL
    public static final String TEST_FIND_BY_ID = "SELECT * FROM tests WHERE test_id = ?";
    //language=SQL
    public static final String TEST_SORTED_BY_NAME = "SELECT * FROM tests ORDER BY subject_name";
    //language=SQL
    public static final String TEST_SORTED_BY_COMPLEXITY = "SELECT * FROM tests ORDER BY complexity";


    //Question
    //language=SQL
    public static final String QUESTION_CREATE = "INSERT INTO test_questions (question_text,test_id) VALUES (?,?)";
    //language=SQL
    public static final String QUESTION_FIND_BY_QUESTION_NAME ="SELECT * FROM test_questions WHERE question_text = ?" ;
    //language=SQL
    public static final String QUESTION_FIND_ALL = "SELECT * FROM test_questions";
    //language=SQL
    public static final String QUESTION_FIND_BY_ID = "SELECT * FROM test_questions WHERE question_id = ?";
    //language=SQL
    public static final String QUESTION_DELETE = "DELETE FROM test_questions WHERE question_id=?";
    //language=SQL
    public static final String QUESTION_FIND_ALL_BY_TEST_ID = "SELECT * FROM test_questions WHERE test_id = ?";

    //Option
    //language=SQL
    public static final String OPTION_CREATE = "INSERT INTO test_options (option_text,option_correct,test_question_id) VALUES (?,?,?)";
    //language=SQL
    public static final String OPTION_FIND_ALL = "SELECT * FROM test_options";
    //language=SQL
    public static final String OPTION_FIND_ALL_BY_QUESTION_ID ="SELECT * FROM test_options WHERE test_question_id = ?";
    //language=SQL
    public static final String OPTION_DELETE_BY_QUESTION_ID = "DELETE FROM test_options WHERE test_question_id=?";
    //language=SQL
    public static final String OPTION_FIND_BY_ID = "SELECT * FROM test_options WHERE option_id = ?";

    //StudentTest
    //language=SQL
    public static final String STUDENT_TEST_CREATE = "INSERT INTO student_tests (test_id,student_id,start_test,end_test) VALUES (?,?,?,?)";
    //language=SQL
    public static final String STUDENT_TEST_FIND_BY_TEST_ID_AND_STUDENT_ID = "SELECT * FROM student_tests WHERE test_id=? AND student_id = ? ";
    //language=SQL
    public static final String STUDENT_TEST_FIND_BY_ID = "SELECT * FROM student_tests WHERE student_tests_id=?";
    //language=SQL
    public static final String STUDENT_TEST_UPDATE = "UPDATE student_tests SET actual_end_test_time=?, result = ? WHERE student_tests_id = ?";
    //language=SQL
    public static final String STUDENT_TEST_FIND_BY_STUDENT_ID = "SELECT * FROM student_tests WHERE student_id=?";
    //language=SQL
    public static final String STUDENT_TEST_CHECK = "SELECT * FROM student_tests WHERE test_id = ? AND student_id = ?";
    //language=SQL
    public static final String STUDENT_TEST_DELETE_BY_TEST_ID = "DELETE FROM student_tests WHERE test_id = ?";

}
