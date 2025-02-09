package team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "grade", nullable = false)
    private int grade;

    @Column(name = "class_number", nullable = false)
    private int classNumber;

    @Column(name = "student_number", nullable = false)
    private int studentNumber;

    @Column(name = "authority", nullable = false)
    private Authority authority;
}
