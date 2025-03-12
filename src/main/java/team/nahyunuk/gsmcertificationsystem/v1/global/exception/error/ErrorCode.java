package team.nahyunuk.gsmcertificationsystem.v1.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(401, "만료된 리프레쉬 토큰입니다."),
    NOT_FOUND_REFRESH_TOKEN(404, "존재하지 않는 리프레시 토큰입니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),
    TOKEN_MALFORMED(400, "형식이 잘못된 토큰입니다."),
    MISSING_TOKEN(400, "토큰이 누락되었습니다."),
    FAILED_JWT_AUTH(401, "jwt 인증에 실패했습니다."),
    REFRESH_TOKEN_MISSING(400, "헤더에 리프레시 토큰을 추가해주세요."),
    FORBIDDEN_ACCESS(403, "접근이 거부되었습니다."),

    // user
    USER_NOT_FOUND(404, "등록된 회원을 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(409, "이미 가입된 회원입니다."),
    EXPIRED_OR_INVALID_CODE(400, "만료되었거나 유효하지 않은 인증 코드입니다."),
    INVALID_CODE(400, "잘못된 인증 코드입니다."),
    INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다."),
    EMAIL_NOT_PENDING(400, "이메일 인증 대기 상태가 아닙니다."),
    INVALID_EMAIL_FORMAT(400, "잘못된 이메일 형식입니다."),
    INVALID_PASSWORD_FORMAT(400, "비밀번호는 영문과 숫자를 포함하여 8자 이상이어야 합니다."),
    PRINCIPAL_INVALID(401, "현재 인증되어 있는 유저의 principal이 유효하지 않습니다."),

    // student
    STUDENT_FILE_EMPTY(400, "업로드한 파일이 비어 있습니다."),
    STUDENT_FILE_PARSE_ERROR(400, "엑셀 파일을 파싱하는 중 오류가 발생했습니다."),
    STUDENT_DATA_SAVE_ERROR(500, "학생 데이터를 저장하는 중 오류가 발생했습니다."),
    STUDENT_NOT_FOUND(404, "존재하지 않는 학생입니다."),

    // image
    FILE_EXTENSION_INVALID(400, "파일 확장자가 유효하지 않습니다."),

    // activity
    ACTIVITY_NOT_FOUND(404, "활동 영역을 찾을 수 없습니다."),

    // book
    BOOK_NOT_FOUND(404, "독서 영역을 찾을 수 없습니다."),

    // major
    MAJOR_NOT_FOUND(404, "전공 영역을 찾을 수 없습니다."),

    // server
    EMAIL_SEND_FAILID(500, "이메일 발송에 실패했습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류"),

    // readingMarathon
    COURSE_NOT_FOUND(404, "해당 독서마라톤 코스를 찾을 수 없습니다."),

    // client
    INVALID_INPUT(400,"잘못된 요청 형식입니다");

    private final int status;
    private final String message;
}
