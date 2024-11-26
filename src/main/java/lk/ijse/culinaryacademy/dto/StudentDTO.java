package lk.ijse.culinaryacademy.dto;

import lk.ijse.culinaryacademy.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String studentId;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String contact;
    private User user;

}
