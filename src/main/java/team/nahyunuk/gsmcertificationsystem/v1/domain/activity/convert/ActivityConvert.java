package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.ActivityGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;

@Component
public class ActivityConvert {

    public ActivityGetResponse toReponse(Activity activity) {
        return new ActivityGetResponse(
                activity.getId(),
                activity.getStudyCategory(),
                activity.getActivityCategory(),
                activity.getSubject(),
                activity.getActivityDate(),
                activity.getPostStatus(),
                activity.getBody().length(),
                activity.getImageUrl()
        );
    }
}
