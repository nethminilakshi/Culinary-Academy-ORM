package lk.ijse.culinaryacademy.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String user_id;
    private String username;
    private String user_email;
    private String user_phone;
    private String user_role;
}
