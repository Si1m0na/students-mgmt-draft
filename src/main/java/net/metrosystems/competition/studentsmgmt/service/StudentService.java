package net.metrosystems.competition.studentsmgmt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.util.StudentUtil;

@Service
public class StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	private static final String FILE_NAME = "resource-data.csv";

	public List<Student> loadStudentsIntoFile(List<Student> students) {
		try {
			StudentUtil su = new StudentUtil();
			su.writeDataToCSV(FILE_NAME, students);
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to write into file!!", e.getMessage());
		}
		return students;
	}
}
