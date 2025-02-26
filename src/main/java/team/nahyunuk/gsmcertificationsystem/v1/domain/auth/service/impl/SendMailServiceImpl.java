package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SendMailRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.SendMailService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    private final static String senderMail = "projectgsm0101@gmail.com";

    @Override
    public CommonApiResponse execute(SendMailRequest request) {
        validateEmailFormat(request.email());
        int verificationCode = getVerificationCode();
        checkSignUpEmail(request.email());
        redisUtil.set(request.email(), String.valueOf(verificationCode), 3);
        sendVerificationEmail(request.email(), verificationCode);
        return CommonApiResponse.success("인증 번호가 발송되었습니다.");
    }

    private void validateEmailFormat(String email) {
        String emailPattern = "^[A-Za-z0-9._%+-]+@gsm\\.hs\\.kr$";
        if (!email.matches(emailPattern)) {
            throw new CustomException(ErrorCode.INVALID_EMAIL_FORMAT);
        }
    }


    private int getVerificationCode() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

    private void checkSignUpEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }
    }

    private void sendVerificationEmail(String email, int verificationCode) {
        try {
            mailSender.send(createMail(email, verificationCode));
        } catch (MailSendException e) {
            throw new CustomException(ErrorCode.EMAIL_SEND_FAILID);
        }
    }


    private MimeMessage createMail(String email, int verificationCode) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(senderMail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            message.setText(buildEmailContent(verificationCode), "UTF-8", "html");
        } catch (MessagingException e) {
          throw new CustomException(ErrorCode.EMAIL_SEND_FAILID);
        }

        return message;
    }

    private String buildEmailContent(int verificationCode) {
        return "<html>" +
                "<body>" +
                "<h2>이메일 인증 코드</h2>" +
                "<p>아래 인증 코드를 입력해주세요:</p>" +
                "<h3 style='color:blue;'>" + verificationCode + "</h3>" +
                "</body>" +
                "</html>";
    }
}
