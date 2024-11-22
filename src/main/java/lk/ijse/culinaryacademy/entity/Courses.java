package lk.ijse.culinaryacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Courses")
    public class Courses{
@Id
private String courseId;
private String courseName;
private String duration;
private double courseFee;

@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
private List<StudentRegDetails> studentRegDetails;

}
