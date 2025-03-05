package team.nahyunuk.gsmcertificationsystem.v1.domain.user.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.user.dto.request.ChangeUserRoleRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface ChangeUserRoleService {
    CommonApiResponse execute(ChangeUserRoleRequest request, String token);
}
