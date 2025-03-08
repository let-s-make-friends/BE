package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;

@RequiredArgsConstructor
public enum ReadingMarathon {
    TURTLE("거북이 코스"),
    CROCODILE("악어 코스"),
    RABBIT("토끼 코스"),
    OSTRICH("타조 코스"),
    LION("사자 코스"),
    TIGER("호랑이 코스"),
    LAUREL("월계관 코스");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ReadingMarathon fromDescription(String value) {
        for (ReadingMarathon readingMarathon : ReadingMarathon.values()) {
            if (readingMarathon.description.equals(value)) {
                return readingMarathon;
            }
        }
        throw new CustomException(ErrorCode.COURSE_NOT_FOUND);
    }
}
