package lk.ijse.culinaryacademy.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String username;
    private String confirmPassword;
    private String userRole;
    private String contact;
}
