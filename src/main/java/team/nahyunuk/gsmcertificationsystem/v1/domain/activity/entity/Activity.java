package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.AgreementStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ApprovalStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

@Entity
@Getter
@Builder
@Table(name = "activity")
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "body")
    private String body;

    @Column(name = "semester")
    private int semester;

    @Column(name = "text_length")
    private int textLength;

    @Column(name = "approval_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approval;

    @Column(name = "agreement_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AgreementStatus agreement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
