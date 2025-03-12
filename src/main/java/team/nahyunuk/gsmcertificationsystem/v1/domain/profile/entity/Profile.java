package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.request.ProfileUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.type.ReadingMarathon;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

import java.util.List;

@Entity
@Getter
@Table(name = "profile")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "toeic_score")
    private int toeicScore;

    @Column(name = "topcit_score")
    private int topcitScore;

    @Column(name = "reading_marathon")
    private ReadingMarathon readingMarathon;

    @ElementCollection
    @CollectionTable(name = "profile_pdf_urls", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "pdf_url")
    @Size(max = 6)
    private List<String> pdfUrls;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student", referencedColumnName = "id")
    private Student student;

    public void update(ProfileUpdateRequest request) {
        this.toeicScore = request.toeicScore();
        this.topcitScore = request.topcitScore();
        this.readingMarathon = request.readingMarathon();
        this.pdfUrls = request.pdfUrl();
    }

}
