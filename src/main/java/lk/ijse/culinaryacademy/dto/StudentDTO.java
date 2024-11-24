package lk.ijse.culinaryacademy.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String studentId;
    private String name;
    private String nic;
    private String address;
    private String email;
    private int contact;

}
