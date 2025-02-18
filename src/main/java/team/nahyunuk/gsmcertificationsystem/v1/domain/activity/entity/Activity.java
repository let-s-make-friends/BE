package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

import java.time.LocalDate;


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

    @Column(name = "study_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyCategory studyCategory;

    @Column(name = "activity_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityCategory activityCategory;

    @Column(name = "body")
    private String body;

    @Column(name = "activity_data")
    private LocalDate activityData;

    @Column(name = "text_length")
    private int textLength;

    @Column(name = "agreement", nullable = false)
    private boolean agreement;

    @Column(name = "post_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
