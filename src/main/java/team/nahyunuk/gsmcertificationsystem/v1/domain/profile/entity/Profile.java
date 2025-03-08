package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.type.ReadingMarathon;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.global.image.pdf.entity.PdfUrl;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_score")
    private int totalScore;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "toeic_score")
    private int toeicScore;

    @Column(name = "topcit_score")
    private int topcitScore;

    @Column(name = "reading_marathon")
    private ReadingMarathon readingMarathon;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(max = 6)
    private List<PdfUrl> pdfUrl = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student", referencedColumnName = "id")
    private Student student;





}
