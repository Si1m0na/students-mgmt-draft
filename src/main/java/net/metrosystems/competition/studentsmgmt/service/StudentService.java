package net.metrosystems.competition.studentsmgmt.service;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.util.StudentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	public static final String FILE_NAME = "resource-data.csv";


	public List<Student> loadStudentsIntoFile(List<Student> students) {
		try {
			StudentUtil su = new StudentUtil();
			su.writeDataToCSV(FILE_NAME, students);
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to write into file!!", e.getMessage());
		}
		return students;
	}

	public boolean deleteAllStudents(){
		StudentUtil su = new StudentUtil();
		return su.deleteContentFromCSVFile(FILE_NAME);
	}

}
