package msherwood.assignment2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT") // table name
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this will allow the id to be automaticaly generated 
    Integer id;

    @Column(name = "STUDENT_NAME") 
    String studentName;

    public Student(){} // default constructor 

// getters and setters 
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    
}
