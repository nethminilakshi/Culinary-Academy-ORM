package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @Column(name = "user_id")
        private String userId;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "user_role")
        private String userRole;

        @Column(name = "contact")
        private String contact;

}
