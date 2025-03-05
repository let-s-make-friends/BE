package team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.dto.StudentDto;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.StudentUploadService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final StudentRepository StudentRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public CommonApiResponse execute(MultipartFile file, String token) {
        User user = findUserByToken(token);
        if (user.getAuthority().equals("ADMIN")) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }

        if (file.isEmpty()) {
            throw new CustomException(ErrorCode.STUDENT_FILE_EMPTY);
        }

        try (InputStream inputStream = file.getInputStream()) {
            List<StudentDto> studentDTOList = parseExcel(inputStream);
            saveStudents(studentDTOList);
        } catch (IOException e) {
            log.error("엑셀 파일을 읽는 중 오류 발생", e);
            throw new CustomException(ErrorCode.STUDENT_FILE_PARSE_ERROR);
        }

        return CommonApiResponse.success("학생 데이터가 저장되었습니다.");
    }

    private User findUserByToken(String token) {
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        return userRepository.findByUserId(Long.valueOf(userId));
    }

    private List<StudentDto> parseExcel(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 가져오기
            List<StudentDto> studentList = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 첫 번째 행(헤더) 건너뛰기

                StudentDto studentDTO = new StudentDto(
                        row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), // email
                        row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), // studentName
                        (int) row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue(), // grade
                        (int) row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue(), // classNumber
                        (int) row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue() // studentNumber
                );

                studentList.add(studentDTO);
            }

            return studentList;
        } catch (Exception e) {
            log.error("엑셀 파일 파싱 중 오류 발생", e);
            throw new CustomException(ErrorCode.STUDENT_FILE_PARSE_ERROR);
        }
    }


    private void saveStudents(List<StudentDto> studentDTOList) {
        try {
            List<Student> students = studentDTOList.stream()
                    .map(dto -> Student.builder()
                            .email(dto.email())
                            .studentName(dto.studentName())
                            .grade(dto.grade())
                            .classNumber(dto.classNumber())
                            .studentNumber(dto.studentNumber())
                            .totalScore(0) // 기본값 0 설정
                            .build())
                    .toList();

            studentRepository.saveAll(students);
        } catch (Exception e) {
            log.error("학생 데이터를 저장하는 중 오류 발생", e);
            throw new CustomException(ErrorCode.STUDENT_DATA_SAVE_ERROR);
        }
    }
}
