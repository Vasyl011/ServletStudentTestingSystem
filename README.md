# ServletStudentTestingSystem

Студент реєструється в системі і після реєстрації може пройти один або кілька Тестів.
В системі існує перелік тестів з Предметів. Для переліку необхідно реалізувати:
- вибір тестів з певного предмету;
- сортування тестів за назвою;
- сортування тестів за складністю;
- сортування тестів за кількістю запитів.
Студент обирає тест і проходить його. На проходження тесту виділяється певний проміжок часу, 
який встановлюється для кожного тесту окремо. Студент має особистий кабінет, 
в якому відображена реєстраційна інформація, а також список тестів, що пройдені, із результатами.
Адміністратор системи:
- створює, видаляє або редагує тести;
- блокує, розблоковує, редагує користувача.
При створенні тесту адміністратор:
- встановлює час проходження тесту;
- складність тесту;
- додає Питання в тест.
Питання може мати одну або кілька правильних відповідей. Результатом проходження тесту є відсоток питань,
 на які студент правильно відповів по відношенню до загальної кількості питань 
(вважається, що студент правильно відповів на питання, 
якщо його відповідь співпадає в точності з правильними варіантами відповідей).

The student registers in the system and after registration can take one or more Tests.
There is a list of tests in the system. For the list it is necessary to implement:
- selection of tests in a particular subject;
- sorting tests by name;
- sorting tests by difficulty;
- sorting tests by the number of requests.
The student chooses the test and passes it. A certain period of time is allocated for passing the test,
which is set for each test separately. The student has a personal account,
which displays registration information, as well as a list of tests passed with the results.
System administrator:
- creates, deletes or edits tests;
- blocks, unlocks, edits the user.
When creating a test, the administrator:
- sets the time of the test;
- complexity of the test;
- adds Questions to the test.
The question may have one or more correct answers. The result of the test is the percentage of questions
 to which the student correctly answered in relation to the total number of questions
(It is considered that the student correctly answered the question
if his answer coincides exactly with the correct answers).

Technologies used:
- Apache Tomcat 8.5.69
- Maven
- MySQL
- HTML
- CSS
- JSP/JSTL
- Bootstrap 4
- Java 8
- Servlet 
