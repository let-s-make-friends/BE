package team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private UUID userId;

    private String email;
    private String username;
    private String password;

    private int grade;
    private int classNumber;
    private int studentNumber;

    private Authority authority;
}
