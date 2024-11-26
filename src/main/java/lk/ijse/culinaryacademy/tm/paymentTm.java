package lk.ijse.culinaryacademy.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class paymentTm {
    private String paymentId;
    private String advancePayment;
    private String paymentDate;
    private String studentId;
    private String courseId;
    private Button btnRemove;
}
