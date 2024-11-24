package lk.ijse.culinaryacademy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Students")

public class Students {
    @Id
    private String studentId;
    private String name;
    private String nic;
    private String email;
    private String address;
    private int contact;


//@OneToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudentRegDetails> studentCourses;}

}





