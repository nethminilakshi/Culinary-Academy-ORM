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
    private double advancePayment;
    private double balancePayment;
    private String paymentDate;


@ManyToOne
    @JoinColumn(name = "studentCourseId")
    private StudentCoursesDetails studentCoursesDetails;

}
