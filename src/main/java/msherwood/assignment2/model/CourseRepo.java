package msherwood.assignment2.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
// allows the resourseService to use crudRepository queries 
public interface CourseRepo  extends CrudRepository<Course, Integer>{
    List<Course> findByStudent(Student student);
    
}
