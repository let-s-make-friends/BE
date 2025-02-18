package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivityCategory {
    SCHOOL_AWARD("교내 수상"),
    OUTSIDE_AWARD("교외 수상"),
    SCHOOL_PARTICIPATION("교내 참가"),
    OUTSIDE_PARTICIPATION("교외 참가"),
    CLUB("동아리"),
    CHARACTER_OUTSIDE_AWARD("인성 교외 수상"),
    CHARACTER_SCHOOL_AWARD("인성 교내 수상"),
    HUMANITIES_ACTIVITY("인문 활동");

    private final String description;

    ActivityCategory(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ActivityCategory fromDescription(String value) {
        for (ActivityCategory category : ActivityCategory.values()) {
            if (category.description.equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + value);
    }
}
