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

    private String studentId;

    private String name;

    private String address;

    private String contact;


    private String NIC;

    private int status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCoursesDetails> studentCourses;
}





