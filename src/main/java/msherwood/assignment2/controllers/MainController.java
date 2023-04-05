package msherwood.assignment2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import msherwood.assignment2.model.Course;
import msherwood.assignment2.model.Student;
import msherwood.assignment2.services.ResourceService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MainController {
    ResourceService resourceService; // a local instance of our service file

    public MainController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

// get mapping for all cources 
    @GetMapping("/Courses") 
    public ResponseEntity<List<Course>> getAllCources() {
        List<Course> courses = resourceService.findAllCourses();
        ResponseEntity<List<Course>> responseEntity;

        if(courses != null) {
            responseEntity = new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

// find course with matching id
    @GetMapping("/Courses/{orderId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer Id) {
        Course course = resourceService.findCourseById(Id);
        ResponseEntity<Course> responseEntity;
        responseEntity = new ResponseEntity<>(course, HttpStatus.OK);
        return responseEntity;
    }


// get mapping for all students 
    @GetMapping("/Students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = resourceService.findAllStudents();
        ResponseEntity<List<Student>> responseEntity;

        if(students != null) {
            responseEntity = new ResponseEntity<>(students, HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/Students/{orderId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer Id) {
        Student student = resourceService.findStudentById(Id);
        ResponseEntity<Student> responseEntity;
        responseEntity = new ResponseEntity<>(student, HttpStatus.OK);
        return responseEntity;
    }
    
}
