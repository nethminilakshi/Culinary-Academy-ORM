package lk.ijse.culinaryacademy.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String userId;
    private String username;
    private String confirmPassword;
    private String userRole;
    private String contact;
}
