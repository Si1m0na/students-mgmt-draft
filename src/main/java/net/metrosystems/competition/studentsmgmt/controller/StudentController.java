package net.metrosystems.competition.studentsmgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.service.StudentService;


@RestController
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/loadStudents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> loadStudentsIntoInputFile(@RequestBody(required = true) List<Student> studentsList) {
    	LOGGER.info("Load all the students");
        return studentService.loadStudentsIntoFile(studentsList);
    }
}
