package team.nahyunuk.gsmcertificationsystem.v1.domain.excel.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ExcelService {

    public void createEmptyExcel(HttpServletResponse response, String fileName) throws IOException {
        // 1. 비어 있는 엑셀 워크북 생성
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 2. HTTP 응답 헤더 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        // 3. 엑셀 데이터를 응답 스트림에 작성
        OutputStream out = response.getOutputStream();
        workbook.write(out);

        // 4. 자원 정리
        out.flush();
        out.close();
        workbook.close();
    }
}