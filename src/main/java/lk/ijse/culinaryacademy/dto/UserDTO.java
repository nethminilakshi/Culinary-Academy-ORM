package lk.ijse.culinaryacademy.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String contact;
    private String email;
    private String userRole;

}
