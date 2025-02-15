package team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private int grade;
    @Column(nullable = false)
    private int classNumber;
    @Column(nullable = false)
    private int studentNumber;


}
