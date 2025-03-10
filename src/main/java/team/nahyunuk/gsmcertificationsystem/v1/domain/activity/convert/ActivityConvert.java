package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.ActivityGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.BodyGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;

@Component
public class ActivityConvert {

    public ActivityGetResponse getActivity(Activity activity) {
        return ActivityGetResponse.builder()
                .id(activity.getId())
                .studyCategory(activity.getStudyCategory())
                .activityCategory(activity.getActivityCategory())
                .subject(activity.getSubject())
                .semester(activity.getSemester())
                .postStatus(activity.getPostStatus())
                .textLength(activity.getBody().length())
                .imageUrl(activity.getImageUrl())
                .build();
    }

    public BodyGetResponse getBody(String body) {
        return new BodyGetResponse(body);
    }
}
