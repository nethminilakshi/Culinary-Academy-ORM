package lk.ijse.culinaryacademy.entity;


import javax.persistence.*;
 import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Students")

public class Students  {
@Id
private String studentId;
private String NIC;
private String Name;
private String Email;
private String Address;
private String Contact;


@OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
private List<StudentRegDetails> studentRegDetails;
}





