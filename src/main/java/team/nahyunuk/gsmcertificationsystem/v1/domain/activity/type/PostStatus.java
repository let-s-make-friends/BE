package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostStatus {
    DRAFT("임시 저장"),
    PUBLISHED("게시");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static PostStatus fromDescription(String value) {
        for (PostStatus status : PostStatus.values()) {
            if (status.description.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown postStatus: " + value);
    }
}
