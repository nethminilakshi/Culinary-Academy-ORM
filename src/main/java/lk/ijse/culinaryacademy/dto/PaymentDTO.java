package lk.ijse.culinaryacademy.dto;

import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private String paymentId;
    private String paymentDate;
    private double payAmount;
    private String status;
    private double upfrontAmount;
    private double balance;
    private StudentCoursesDetails student_course;

}
