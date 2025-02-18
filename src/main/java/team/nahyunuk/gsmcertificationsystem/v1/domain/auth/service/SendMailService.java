package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SendMailRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface SendMailService {
    CommonApiResponse execute(SendMailRequest request);
}
