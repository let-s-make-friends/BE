package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.request;

import jakarta.validation.constraints.Size;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.type.ReadingMarathon;

import java.util.List;

public record ProfileUpdateRequest(
        ReadingMarathon readingMarathon,
        @Size(max = 6) List<String> pdfUrl
        ) {
}
