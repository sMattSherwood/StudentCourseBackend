package msherwood.assignment2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import msherwood.assignment2.model.Course;
import msherwood.assignment2.model.CourseRepo;
import msherwood.assignment2.model.Student;
import msherwood.assignment2.model.StudentRepo;

@Service
public class ResourceService {
    StudentRepo studentRepo;
    CourseRepo courseRepo;

    // constructor for recource service 
    public ResourceService(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    // creating queries using the crud repository from the course and student repos 


 // this will be used in the main controller to find all courses 
    public List<Course> findAllCourses() {
        return (List<Course>) courseRepo.findAll();
    }

    public Course findCourseById(Integer Id) {
        return courseRepo.findById(Id).get();
    }

// will be used by the main controller to find all the students 
    public List<Student> findAllStudents() {
        return (List<Student>) studentRepo.findAll();
    }

    public Student findStudentById(Integer Id) {
        return studentRepo.findById(Id).get();
    }

// this will allow the main controller to 
    public List<Course> findCourseByStudent(Student student) {
        return courseRepo.findByStudent(student);
    }

// save data to student
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }
// save data to course
    public void saveCourse(Course course) {
        courseRepo.save(course);
    }
// updates the student
    public Student updateStudent(Student newStudent, Integer Id) {
        // stores the value of the serched student
        Student student = studentRepo.findById(Id).get();
        if(student != null) {
            student.setStudentName(newStudent.getStudentName());
            studentRepo.save(student);
            return student;
        }
        else {
            return null;
        }
    }
// delete student object

    public boolean deleteStudent(Integer Id) {
        if(!studentRepo.findById(Id).isEmpty()) {
            studentRepo.deleteById(Id);
            return true;
        }
        else
            return false;
        
    }

// delete courses 
    public Boolean deleteCourses(Integer Id) {
        if(!courseRepo.findById(Id).isEmpty()) {
            courseRepo.deleteById(Id);
            return true;
        }
        else 
            return false;
    }








    
}
