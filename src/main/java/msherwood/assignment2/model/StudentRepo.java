package msherwood.assignment2.model;

import org.springframework.data.repository.CrudRepository;
// allows ResourseService to use crudRepository queries 
public interface StudentRepo extends CrudRepository<Student, Integer> {
    
}
