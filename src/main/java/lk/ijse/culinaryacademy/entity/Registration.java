//package lk.ijse.culinaryacademy.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "registration")
//public class Registration {
//    @Id
//    @Column(name = "register id", length = 100)
//    private String registerId;
//
//    @Column(name = "student id", length = 100)
//    private String studentId;
//
//    @Column(name = "course id", length = 100)
//    private String courseId;
//
//    @Column(name = "register_date", length = 100)
//    private String registerDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id", insertable = false, updatable = false)
//    private Student student;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_id", insertable = false, updatable = false)
//    private Course course;
//
//
//}
