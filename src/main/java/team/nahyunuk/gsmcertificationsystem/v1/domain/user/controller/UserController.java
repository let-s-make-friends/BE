package team.nahyunuk.gsmcertificationsystem.v1.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.dto.request.ChangeUserRoleRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.service.impl.ChangeUserRoleServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final ChangeUserRoleServiceImpl changeUserRoleService;

    @PostMapping
    public CommonApiResponse changeRole(@RequestBody ChangeUserRoleRequest request,
                                        @RequestHeader("Authorization") String token) {
        return changeUserRoleService.execute(request, token);
    }
}
