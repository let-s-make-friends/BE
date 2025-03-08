package team.nahyunuk.gsmcertificationsystem.v1.global.image.pdf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;

@Entity
@Getter
@Builder
@Table(name = "pdf_url")
@AllArgsConstructor
@NoArgsConstructor
public class PdfUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "url", nullable = false)
    private String url;
}
