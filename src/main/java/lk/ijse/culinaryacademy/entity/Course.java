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
@Table(name = "course")

public class Course {
    @Id
    private String courseId;

    private String courseName;


    private String duration;

    private double courseFee;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<StudentCoursesDetails> studentCourses;

}
