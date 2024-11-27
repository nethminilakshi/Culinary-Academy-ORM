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
   @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "fee")
    private double advancePayment;

    @Column(name = "studentId")
    private String studentId;

    @Column(name = "courseId")
    private String courseId;

    @Column(name = "paymentDate")
    private String paymentDate;


}
