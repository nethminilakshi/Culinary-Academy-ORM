package lk.ijse.culinaryacademy.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTm {

    private String userId;
    private String userName;
    private String password;
    private String role;

}
