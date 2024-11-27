package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "StudentCourseDetails")
public class StudentCoursesDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_course_id;
    private Date registration_date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


}
