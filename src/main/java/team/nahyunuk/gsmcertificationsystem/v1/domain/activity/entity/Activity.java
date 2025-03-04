package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityUpdateRequest;
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

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Column(name = "semester")
    @Min(1)
    @Max(2)
    private int semester;

    @Column(name = "text_length")
    private int textLength;

    @Column(name = "agreement", nullable = false)
    private boolean agreement;

    @Column(name = "post_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public void update(ActivityUpdateRequest request) {
        this.id = request.id();
        this.studyCategory = request.studyCategory();
        this.activityCategory = request.activityCategory();
        this.subject = request.subject();
        this.body = request.body();
        this.semester = request.semester();
        this.imageUrl = request.imageUrl();
        this.postStatus = request.postStatus();
    }
}
