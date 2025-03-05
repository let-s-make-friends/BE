package team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.dto.response.StudentDto;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.StudentUploadService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentUploadServiceImpl implements StudentUploadService {

    private final StudentRepository studentRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public CommonApiResponse execute(MultipartFile file, String token) {
        User user = tokenProvider.findUserByToken(token);
        validateUserPermission(user);
        validateFile(file);

        try (InputStream inputStream = file.getInputStream()) {
            List<StudentDto> studentDtoList = parseExcel(inputStream);
            saveStudents(studentDtoList);
        } catch (IOException e) {
            log.error("엑셀 파일을 읽는 중 오류 발생", e);
            throw new CustomException(ErrorCode.STUDENT_FILE_PARSE_ERROR);
        }

        return CommonApiResponse.success("학생 데이터가 저장되었습니다.");
    }

    private void validateUserPermission(User user) {
        if (user.getAuthority() == Authority.ADMIN || user.getAuthority() == Authority.TEACHER) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new CustomException(ErrorCode.STUDENT_FILE_EMPTY);
        }
    }

    private List<StudentDto> parseExcel(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<StudentDto> studentList = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                StudentDto studentDto = new StudentDto(
                        row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                        row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                        (int) row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue(),
                        (int) row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue(),
                        (int) row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue()
                );

                studentList.add(studentDto);
            }

            return studentList;
        } catch (Exception e) {
            log.error("엑셀 파일 파싱 중 오류 발생", e);
            throw new CustomException(ErrorCode.STUDENT_FILE_PARSE_ERROR);
        }
    }

    private void saveStudents(List<StudentDto> studentDtoList) {
        try {
            List<Student> students = studentDtoList.stream()
                    .map(dto -> Student.builder()
                            .email(dto.email())
                            .studentName(dto.studentName())
                            .grade(dto.grade())
                            .classNumber(dto.classNumber())
                            .studentNumber(dto.studentNumber())
                            .totalScore(0)
                            .build())
                    .toList();

            studentRepository.saveAll(students);
        } catch (Exception e) {
            log.error("학생 데이터를 저장하는 중 오류 발생", e);
            throw new CustomException(ErrorCode.STUDENT_DATA_SAVE_ERROR);
        }
    }
}
