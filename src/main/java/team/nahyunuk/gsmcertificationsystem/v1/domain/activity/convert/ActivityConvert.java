package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.ActivityGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.BodyGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;

@Component
public class ActivityConvert {

    public ActivityGetResponse getActivity(Activity activity) {
        return new ActivityGetResponse(
                activity.getId(),
                activity.getStudyCategory(),
                activity.getActivityCategory(),
                activity.getSubject(),
                activity.getSemester(),
                activity.getPostStatus(),
                activity.getBody().length(),
                activity.getImageUrl()
        );
    }

    public BodyGetResponse getBody(String body) {
        return new BodyGetResponse(body);
    }
}
