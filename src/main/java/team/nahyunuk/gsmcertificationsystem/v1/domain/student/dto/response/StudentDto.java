package team.nahyunuk.gsmcertificationsystem.v1.domain.student.dto;

public record StudentDto(
        String email,
        String studentName,
        int grade,
        int classNumber,
        int studentNumber
) {
}
