package team.nahyunuk.gsmcertificationsystem.v1.domain.excel.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.nahyunuk.gsmcertificationsystem.v1.domain.excel.service.ExcelService;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ExcelController {
    private final ExcelService excelService;

    @GetMapping
    public void getExcel(HttpServletResponse response) throws IOException {
        excelService.createEmptyExcel(response, "empty_file");
    }
}
