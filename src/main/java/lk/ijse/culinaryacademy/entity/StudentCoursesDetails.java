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


}
