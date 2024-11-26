package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Courses")

public class Courses {
    @Id
    private String courseId;
    private String courseName;
    private String duration;
    private double courseFee;

//@OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
//private List<StudentRegDetails> studentRegDetails;


}
