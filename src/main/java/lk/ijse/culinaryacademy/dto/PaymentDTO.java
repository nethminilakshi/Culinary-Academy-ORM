package lk.ijse.culinaryacademy.dto;

import lk.ijse.culinaryacademy.entity.StudentCoursesDetails;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String paymentID;
    private double advancePayment;
    private double balancePayment;
    private String paymentDate;
    private StudentCoursesDetails studentCoursesDetails;


}
