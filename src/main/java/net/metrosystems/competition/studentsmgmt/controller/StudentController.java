package net.metrosystems.competition.studentsmgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.service.StudentService;


@RestController
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    //method type: post             - path: /load-students           - return: List<Student> - DONE
    //method type: delete           - path: /delete-all-students    - return: boolean - DONE
    //TODO: method type: get         - path: /all-students            - return: List<Student>  - returneaza in response body toti studentii din fisier
    //TODO: method type: get         - path: /students/{month}       - return: List<Student>  - returneaza in response body toti studentii nascuti in luna {month}
    //TODO: method type: put         - path: /update/{id}            - return: List<Student> - updateaza numele(first name) studentului cu {id}, folosind valoarea preluata din request body (MediaType.TEXT_PLAIN_VALUE)
    //TODO: method type: delete      - path: /student/{id}           - return: List<Student> - sterge studentul din fisier cu {id} si returneaza in responde body toate informatiile despre studentul sters

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/load-students", consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<Student> loadStudentsIntoInputFile(@RequestBody List<Student> studentsList) {
        return studentService.loadStudentsIntoFile(studentsList);
    }

    @DeleteMapping("/delete-all-students")
    public boolean deleteAll(){
        return studentService.deleteAllStudents();
    }
}
