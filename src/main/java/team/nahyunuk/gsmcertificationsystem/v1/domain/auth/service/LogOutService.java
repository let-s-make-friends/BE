package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;

public interface LogOutService {
    CommonApiResponse execute(String token);
}
