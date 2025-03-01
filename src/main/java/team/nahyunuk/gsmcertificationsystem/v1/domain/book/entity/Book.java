package team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "page")
    private int page;

    @Column(name = "body")
    private String body;

    @Column(name = "book_date")
    private LocalDate bookDate;

    @Column(name = "text_length")
    private int textLength;

    @Column(name = "agreement")
    private boolean agreement;

    @Column(name = "post_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
