package msherwood.assignment2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSE") // table name 
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will automaticly create the id for the data row
    Integer id;
    
    @Column(name = "COURSE_NAME")
    String courseName;

    @Column(name = "COURSE_DECRIPTION")
    String courseDecription;
// one student can have many courses
    @ManyToOne
    Student student;

    public Course(){
        courseName = "?";
        courseDecription = "??";
    }

// getters and setters 
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDecription() {
        return this.courseDecription;
    }

    public void setCourseDecription(String courseDecription) {
        this.courseDecription = courseDecription;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    
}
