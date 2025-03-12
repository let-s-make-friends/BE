package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foreign_language")
public class Foreign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "toeic_score", nullable = false)
    private int toeicScore;

    @Column(name = "toefl_score", nullable = false)
    private int toeflScore;

    @Column(name = "toeic_speaking_score", nullable = false)
    private int toeicSpeakingScore;

    @Column(name = "opic_score", nullable = false)
    private int opicScore;

    @Column(name = "jpt_score", nullable = false)
    private int jptScore;

    @Column(name = "cpt_score", nullable = false)
    private int cptScore;

    @Column(name = "hsk_score", nullable = false)
    private int hskScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
