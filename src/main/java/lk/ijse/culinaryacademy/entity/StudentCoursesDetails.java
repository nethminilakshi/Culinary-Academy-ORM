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
@Table(name = "StudentRegDetails")
public class StudentCoursesDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentCourseId;
    private Date registrationDate;

  @ManyToOne
    @JoinColumn(name = "studentId")
    private Students student;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Courses course;

    @OneToMany(mappedBy = "StudentRegDetails", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
