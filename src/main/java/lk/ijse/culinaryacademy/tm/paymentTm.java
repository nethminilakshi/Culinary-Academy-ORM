package lk.ijse.culinaryacademy.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class paymentTm {
    private String paymentId;
    private double advancePayment;
    private double balancePayment;
    private String paymentDate;
    private String studentId;
    private String studentName;
    private String nic;
    private String courseId;
    private String courseName;
    private double courseFee;
    private Button btnRemove;
}
