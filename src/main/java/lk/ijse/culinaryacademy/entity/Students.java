package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "students")  // Table name in lowercase for consistency
public class Students {
    @Id
    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "nic", unique = true, length = 12)
    private String nic;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "contact", length = 15)
    private int contact;  // Changed to String





//@OneToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudentRegDetails> studentCourses;}

}





