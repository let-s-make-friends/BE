package team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

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

    @Column(name = "semester")
    @Min(1)
    @Max(2)
    private int semester;

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

    public void update(BookUpdateRequest request) {
        this.id = request.id();
        this.title = request.title();
        this.author = request.author();
        this.page = request.page();
        this.body = request.body();
        this.semester = request.semester();
        this.postStatus = request.postStatus();
    }
}
