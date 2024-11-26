package lk.ijse.culinaryacademy.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTm {
    private  String registrationId;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private String registerDate;
}
