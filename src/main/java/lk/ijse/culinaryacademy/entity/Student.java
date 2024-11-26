package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student")  // Table name in lowercase for consistency
public class Student {
    @Id
    @Column(name = "student_id" , length = 100)

    private String studentId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "nic", unique = true, length = 20)
    private String nic;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "contact", length = 20)
    private String contact;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
private User user;



}





