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
    @Column(name = "student_id", length = 100)
    private String studentId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "contact", length = 20)
    private String contact;


    @Column(name = "nic", unique = true, length = 30)
    private String NIC;

    @Column(name = "status")
    private int status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCoursesDetails> studentCourses;
}





