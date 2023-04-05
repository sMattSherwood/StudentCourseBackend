package msherwood.assignment2.model;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Integer> {
    
}
