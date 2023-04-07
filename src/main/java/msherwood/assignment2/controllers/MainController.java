package msherwood.assignment2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import msherwood.assignment2.model.Course;
import msherwood.assignment2.model.Student;
import msherwood.assignment2.services.ResourceService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090") // sends the CORS the propper http requests without getting an error for cross site scripting
public class MainController {
    ResourceService resourceService; // a local instance of our service file

    // injecting the resourse service into the constructor for the main controller 
    public MainController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

// get mapping for all cources 
    @GetMapping("/Courses") 
    public ResponseEntity<List<Course>> getAllCources() {
    // stores all of the courses into this list
        List<Course> courses = resourceService.findAllCourses();
        ResponseEntity<List<Course>> responseEntity;
    // checks to see if the list is empty or full and sends the data and the propper request staus
        if(courses != null) {
            responseEntity = new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

// find course with matching id
    @GetMapping("/Courses/{Id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer Id) {
    // using the Id passed from the params the course will find the student 
        Course course = resourceService.findCourseById(Id);
        ResponseEntity<Course> responseEntity;
        responseEntity = new ResponseEntity<>(course, HttpStatus.OK);
        return responseEntity;
    }
// post mapping for courses
    @PostMapping("/Courses/{Id}")
    public ResponseEntity<Integer> postCourse(@RequestBody Course newCourse, @PathVariable Integer Id ) {
    // passes the id to find a spesific student
        Student student = resourceService.findStudentById(Id);
    // if student is not empty it will add a course onto the student and pass a created http status
        if(student != null) {
            newCourse.setStudent(student);
            resourceService.saveCourse(newCourse);
            return new ResponseEntity<>(newCourse.getId(), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
// delete mapping for courses 
    @DeleteMapping("/Courses{Id}")
    public ResponseEntity<Integer> deleteCourse(@PathVariable Integer Id) {
    // this will use the deleteCourse from the resourse service using the Id passed and send either a ok http request if it finds the id
        if(resourceService.deleteCourses(Id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

// get mapping for all students 
    @GetMapping("/Students")
    public ResponseEntity<List<Student>> getAllStudents() {
    // store all students into the list
        List<Student> students = resourceService.findAllStudents();
        ResponseEntity<List<Student>> responseEntity;
    // if not empty it will return the data and a ok http request
        if(students != null) {
            responseEntity = new ResponseEntity<>(students, HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
// find student based off of the id 
    @GetMapping("/Students/{Id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer Id) {
    // using the passed in data it will find the student using the id
        Student student = resourceService.findStudentById(Id);
        ResponseEntity<Student> responseEntity;
        responseEntity = new ResponseEntity<>(student, HttpStatus.OK);
        return responseEntity;
    }
// post mapping for student 
    @PostMapping("/Students")
    public ResponseEntity<Integer> postStudent(@RequestBody Student student) {
    // with will create a new student using the resourseService query
        resourceService.saveStudent(student);
        return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);

    }
// put mapping for student
    @PutMapping("/Students/{Id}")
    public ResponseEntity<Integer> putStudent(@RequestBody Student newStudent, @PathVariable Integer Id) {
    // this will update the student name
        if(resourceService.updateStudent(newStudent, Id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
// delete mapping for student
    @DeleteMapping("Students/{Id}")
    public ResponseEntity<Integer> deleteStudent(@PathVariable Integer Id) {
    // using the passed in id it will find the student and use the resourse service to delete the student
        if(resourceService.deleteStudent(Id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
// get courses by using one student 
    @GetMapping("/Students/{Id}/Courses")
    public ResponseEntity<List<Course>> getCoursesByStudent(@PathVariable Integer Id) {
    // using the passed in param it will find the student
        Student existingStudent = resourceService.findStudentById(Id);
    // this will store all courses under the student 
        List<Course> studentCourses = new ArrayList<>();
    // using a query from resourceService it will find the course by the student passed 
        studentCourses = resourceService.findCourseByStudent(existingStudent);
        ResponseEntity<List<Course>> responseEntity;
    // this will pass the student courses assocetated with the student and a http response of ok
        responseEntity = new ResponseEntity<>(studentCourses, HttpStatus.OK);
        return responseEntity;
    }

// 

    
}
