package team.nahyunuk.gsmcertificationsystem.v1.domain.user.dto.request;

import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;

public record ChangeUserRoleRequest(
        Long userId,
        Authority authority
) {
}
