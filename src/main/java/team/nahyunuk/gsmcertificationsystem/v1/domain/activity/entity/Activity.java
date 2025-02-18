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
    private StudyCategory studyCategory;

    @Column(name = "activity_category", nullable = false)
    private ActivityCategory activityCategory;

    @Column(name = "body")
    private String body;

    @Column(name = "semester")
    private int semester;

    @Column(name = "text_length")
    private int textLength;

    @Column(name = "agreement", nullable = false)
    private boolean agreement = false;

    @Column(name = "post_status", nullable = false)
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
