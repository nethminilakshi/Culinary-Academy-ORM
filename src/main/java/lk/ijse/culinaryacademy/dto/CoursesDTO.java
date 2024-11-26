package lk.ijse.culinaryacademy.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesDTO {
    private String courseId;
    private String courseName;
    private String duration;
    private String courseFee;
}
