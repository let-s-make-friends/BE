package team.nahyunuk.gsmcertificationsystem.v1.domain.user.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface GerUserService {
    CommonApiResponse execute(String token);
}
