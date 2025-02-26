package team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", unique = true, nullable = false, updatable = false)
    private String email;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "grade", nullable = false)
    private int grade;

    @Column(name = "class_number", nullable = false)
    private int classNumber;

    @Column(name = "student_number", nullable = false)
    private int studentNumber;

    @Column(name = "totalScore")
    private int totalScore = 0;

}
