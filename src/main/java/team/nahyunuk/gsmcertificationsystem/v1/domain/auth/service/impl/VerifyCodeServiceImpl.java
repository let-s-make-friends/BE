package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.VerifyCodeService;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;

@Service
@RequiredArgsConstructor
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private final RedisUtil redisUtil;

    @Override
    public CommonApiResponse execute(String email, String  code){
        String storedCode = redisUtil.get(email).toString();
        checkVerifyCode(storedCode, code);
        redisUtil.set("verified", email, 60);
        redisUtil.delete(email);
        return CommonApiResponse.success("인증되었습니다");
    }

    private void checkVerifyCode(String storedCode, String code){
        if(!storedCode.equals(code)){
            throw new CustomException(ErrorCode.INVALID_CODE);
        }
    }
}
