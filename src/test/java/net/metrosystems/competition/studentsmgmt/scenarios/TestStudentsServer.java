package net.metrosystems.competition.studentsmgmt.scenarios;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.jupiter.CitrusExtension;
import com.consol.citrus.dsl.runner.TestRunner;
import net.metrosystems.competition.studentsmgmt.CTBaseScenarios;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({CitrusExtension.class})
public class TestStudentsServer extends CTBaseScenarios {

    //method type: post             - path: /load-students           - return: List<Student> - DONE
    //method type: delete           - path: /delete-all-students    - return: boolean - DONE
    //TODO: method type: get         - path: /all-students            - return: List<Student>  - returneaza in response body toti studentii din fisier
    //TODO: method type: get         - path: /students/{month}       - return: List<Student>  - returneaza in response body toti studentii nascuti in luna {month}
    //TODO: method type: put         - path: /update/{id}            - return: List<Student> - updateaza numele studentul cu {id}, folosind valoarea preluata din request body
    //TODO: method type: delete      - path: /student/{id}           - return: List<Student> - sterge studentul din fisier cu {id}, si returneaza in responde body toate informatiile despre studentul sters


    public static final String PUT_PATH_LOAD_STUDENTS = "/load-students";
    public static final String DELETE_PATH_STUDENTS = "/delete-all-students";
    public static final String GET_PATH_ALL_STUDENTS = "/all-students";
    public static final String GET_STUDENT_MONTH = "/students/%s";
    public static final String PUT_UPDATE_STUDENT = "/update/%s";
    public static final String DELETE_STUDENT = "/student/%s";

    public static final String PATH_EXPECTED_VASILE_STUDENT_678 = "test_data/expected-vasile-student.json";
    public static final String PATH_INPUT_VASILE_STUDENT = "test_data/expected-vasile-student.json";
    public static final String PATH_EXPECTED_NELU_UPDATED_NAME_555 = "test_data/expected-nelu-updated-name.json";

    public static final String PATH_INPUT_VASILE_AND_JEU_STUDENTS = "test_data/expected-vasile-and-jeu-students.json";
    public static final String PATH_INPUT_VASILE_JEU_AND_NELU_STUDENTS = "test_data/expected-vasile-jeu-and-nelu-students.json";

    /**
     * delete dummy data
     * method type: post     - path: /loadStudents
     * method type: get      - path: /allStudents
     */
    @Test
    @CitrusTest
    void testMethods_loadStudents_and_allStudents(@CitrusResource TestRunner runner) {

        deleteContentFromCSV(runner, DELETE_PATH_STUDENTS); // remove dummy data from CSV file.
        callEndpointLoadStudents(runner, PUT_PATH_LOAD_STUDENTS, PATH_INPUT_VASILE_STUDENT, PATH_INPUT_VASILE_STUDENT);
        callEndpointGetAllStudents(runner, GET_PATH_ALL_STUDENTS, PATH_EXPECTED_VASILE_STUDENT_678);
    }

    /**
     *     method type: post     - path: /loadStudents
     *     method type: put      - path: /update/{id}
     */
    @Test
    @CitrusTest
    void testMethods_loadStudents_and_update_student_via_id(@CitrusResource TestRunner runner) {
        deleteContentFromCSV(runner, DELETE_PATH_STUDENTS); // remove dummy data from CSV file.
        callEndpointLoadStudents(runner, PUT_PATH_LOAD_STUDENTS, PATH_INPUT_VASILE_STUDENT, PATH_INPUT_VASILE_STUDENT);
        callUpdateStudentName(runner, PUT_UPDATE_STUDENT, "678","NELU", PATH_EXPECTED_NELU_UPDATED_NAME_555);
    }

    /**
     *     method type: post     - path: /loadStudents
     *     method type: delete   - path: /delete/{id}
     */
    @Test
    @CitrusTest
    void test_methods_loadStudents_and_delete_student_via_id(@CitrusResource TestRunner runner) {
        deleteContentFromCSV(runner, DELETE_PATH_STUDENTS); // remove dummy data from CSV file.
        callEndpointLoadStudents(runner, PUT_PATH_LOAD_STUDENTS, PATH_INPUT_VASILE_AND_JEU_STUDENTS, PATH_INPUT_VASILE_AND_JEU_STUDENTS);
        callDeleteStudent(runner, DELETE_STUDENT, "678", PATH_EXPECTED_VASILE_STUDENT_678);
    }

    /**
     *     method type: post     - path: /loadStudents
     *     method type: delete   - path: /students/{id}
     */
    @Test
    @CitrusTest
    void test_methods_loadStudents_and_get_students_born_in_specific_month(@CitrusResource TestRunner runner) {
        deleteContentFromCSV(runner, DELETE_PATH_STUDENTS); // remove dummy data from CSV file.
        callEndpointLoadStudents(runner, PUT_PATH_LOAD_STUDENTS, PATH_INPUT_VASILE_JEU_AND_NELU_STUDENTS, PATH_INPUT_VASILE_JEU_AND_NELU_STUDENTS);
        callGetAllStudentsWhichAreBornInMonth(runner, GET_STUDENT_MONTH, "11", PATH_INPUT_VASILE_AND_JEU_STUDENTS);
    }
}
