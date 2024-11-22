package lk.ijse.culinaryacademy.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "StudentRegDetails")
public class StudentRegDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentRegId;

  @ManyToOne
    @JoinColumn(name = "studentId")
    private Students student;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Courses course;


}
