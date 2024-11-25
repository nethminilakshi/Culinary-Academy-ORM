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
    @Column(name = "studentId")

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
    private int contact;





//@OneToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudentRegDetails> studentCourses;}

}





