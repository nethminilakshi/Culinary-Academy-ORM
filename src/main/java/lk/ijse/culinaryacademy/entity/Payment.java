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


    private String studentId;


    private String courseId;


    private String paymentDate;


}
