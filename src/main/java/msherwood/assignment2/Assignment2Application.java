package msherwood.assignment2;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import msherwood.assignment2.model.Course;
import msherwood.assignment2.model.CourseRepo;
import msherwood.assignment2.model.Student;
import msherwood.assignment2.model.StudentRepo;

@SpringBootApplication
public class Assignment2Application implements CommandLineRunner{

	Logger logger;
	StudentRepo studentRepo;
	CourseRepo courseRepo;

	// default constructor 
	public Assignment2Application(CourseRepo courseRepo, StudentRepo studentRepo) {
		this.courseRepo = courseRepo;
		this.studentRepo = studentRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// this will help poulate the data base with dummy data
		Lorem lorem = LoremIpsum.getInstance();

		// values used for the for loop
		Integer nStudents = 5, nCoursesPerStudent = 3;

		switch(args.length) {
			case 1: nStudents = Integer.parseInt(args[0]);
			case 2: nCoursesPerStudent = Integer.parseInt(args[1]);
		}

		for(int i =1; i <=nStudents; i++) {
			Student student = new Student();
			student.setStudentName(lorem.getName());// this will set the name of all students to latin words 
			studentRepo.save(student); // saves this onto the data base 

			for(int j = 1; j <= nCoursesPerStudent; j++) {
				Course course = new Course();
				course.setCourseName(lorem.getTitle(1)); // this will set the name of all courses to latin words 
				course.setCourseDecription(lorem.getWords(5, 10)); // this will set the decription
				course.setStudent(student); // this will assign the students onto this table 
				courseRepo.save(course); // saves this to the database 
			}
		}
	}

}
