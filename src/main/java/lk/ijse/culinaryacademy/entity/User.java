package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "user")
    public class User {
        @Id
        private String userId;
        private String username;
        private String confirmPassword;
        private String userRole;
        private String contact;
}
