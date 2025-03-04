package team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

@Entity
@Getter
@Builder
@Table(name = "major_domain")
@AllArgsConstructor
@NoArgsConstructor
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "award_count")
    @Max(6)
    private int awardCount;

    @Column(name = "license_count")
    @Max(6)
    private int licenseCount;

    @Column(name = "topcit_score")
    @Max(1000)
    private int topcitScore;

    @Column(name = "club_count")
    @Max(2)
    private int clubCount;

    @Column(name = "competition_count")
    @Max(8)
    private int competitionCount;

    @Column(name = "school_competition_count")
    @Max(3)
    private int schoolCompetitionCount;

    @Column(name = "special_lecture_count")
    @Max(4)
    private int specialLectureCount;

    @Column(name = "after_school_count")
    @Max(2)
    private int afterSchoolCount;

    @Column(name = "major_total")
    private int majorTotal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
