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
  @Column(name = "course_id", length = 100)
    private String courseId;

    @Column(name = "course_name", length = 100)
    private String courseName;


    @Column(name = "duration", length = 100)
    private String duration;

    @Column(name = "fee", length = 100)
    private double courseFee;
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<StudentCoursesDetails> studentCourses;

}
