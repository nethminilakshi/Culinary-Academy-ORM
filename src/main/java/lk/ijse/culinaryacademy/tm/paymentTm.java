package lk.ijse.culinaryacademy.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class paymentTm {
    private String paymentId;
    private String status;
    private double upfrontAmount;
    private double balance;
    private String studentId;
    private String courseId;
    private Long student_course_id;
    private Button btnRemove;
}
