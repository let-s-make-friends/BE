package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.response.ForeignGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;

@Component
public class ForeignConvert {

    public ForeignGetResponse getResponse(Foreign foreign) {
        return ForeignGetResponse.builder()
                .toeicScore(foreign.getToeicScore())
                .toeflScore(foreign.getToeflScore())
                .tepsScore(foreign.getTepsScore())
                .toeicSpeakingLevel(foreign.getToeicSpeakingLevel())
                .opicScore(foreign.getOpicScore())
                .jptScore(foreign.getJptScore())
                .hskScore(foreign.getHskScore())
                .cptScore(foreign.getCptScore())
                .build();
    }

}
