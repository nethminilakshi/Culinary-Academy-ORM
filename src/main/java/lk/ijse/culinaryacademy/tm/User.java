package lk.ijse.culinaryacademy.tm;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String userId;
    private String userName;
    private String password;
    private String role;

}
