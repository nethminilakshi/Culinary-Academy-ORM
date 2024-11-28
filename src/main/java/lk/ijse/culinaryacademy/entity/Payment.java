package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id

    private String paymentId;
    private String payDate;
    private double payAmount;
    private String status;
    private double upfrontAmount;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "student_course_id")
    private StudentCoursesDetails StudentCourseDetails;

}
