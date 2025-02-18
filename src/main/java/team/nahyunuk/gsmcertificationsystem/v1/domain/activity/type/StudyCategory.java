package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StudyCategory {
    HUMANITIES("인문"),
    MAJOR("전공");

    private final String decription;

    @JsonValue
    public String getDecription() {
        return decription;
    }

    @JsonCreator
    public static StudyCategory fromDescription(String value) {
        for (StudyCategory category : StudyCategory.values()) {
            if (category.decription.equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
