package lk.ijse.culinaryacademy.tm;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesTm {
    private String courseId;
    private String courseName;
    private String duration;
    private double courseFee;
}
