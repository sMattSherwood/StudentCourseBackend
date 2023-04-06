package msherwood.assignment2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
// post mapping for courses
    @PostMapping("/Courses/{orderId}")
    public ResponseEntity<Integer> postCourse(@RequestBody Course newCourse, @PathVariable Integer Id ) {
        Student student = resourceService.findStudentById(Id);

        if(student != null) {
            newCourse.setStudent(student);
            resourceService.saveCourse(newCourse);
            return new ResponseEntity<>(newCourse.getId(), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
// delete mapping for courses 
    @DeleteMapping("/Courses{orderId}")
    public ResponseEntity<Integer> deleteCourse(@PathVariable Integer Id) {
        if(resourceService.deleteCourses(Id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
// find student based off of the id 
    @GetMapping("/Students/{orderId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer Id) {
        Student student = resourceService.findStudentById(Id);
        ResponseEntity<Student> responseEntity;
        responseEntity = new ResponseEntity<>(student, HttpStatus.OK);
        return responseEntity;
    }
// put mapping for student
    @PutMapping("/Students/{orderId}")
    public ResponseEntity<Integer> putStudent(@RequestBody Student newStudent, @PathVariable Integer Id) {
        if(resourceService.updateStudent(newStudent, Id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
// delete mapping for student
    @DeleteMapping("Students/{orderId}")
    public ResponseEntity<Integer> deleteStudent(@PathVariable Integer Id) {
        if(resourceService.deleteStudent(Id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
// get courses by using one student 
    @GetMapping("/Students/{orderId}/Courses")
    public ResponseEntity<List<Course>> getCoursesByStudent(@PathVariable Integer Id) {
        Student existingStudent = resourceService.findStudentById(Id);
        List<Course> studentCourses = new ArrayList<>();

        studentCourses =resourceService.findCourseByStudent(existingStudent);
        ResponseEntity<List<Course>> responseEntity;
        responseEntity = new ResponseEntity<>(studentCourses, HttpStatus.OK);
        return responseEntity;
    }

// 

    
}
