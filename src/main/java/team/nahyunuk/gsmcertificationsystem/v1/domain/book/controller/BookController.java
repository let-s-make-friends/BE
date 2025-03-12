package team.nahyunuk.gsmcertificationsystem.v1.domain.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookPostRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl.BookBodyGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl.BookGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl.BookPostServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl.BookUpdateServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookGetServiceImpl bookGetService;
    private final BookPostServiceImpl bookPostService;
    private final BookUpdateServiceImpl bookUpdateService;
    private final BookBodyGetServiceImpl bodyGetService;

    @PostMapping
    public CommonApiResponse post(@Valid @RequestBody BookPostRequest request) {
        return bookPostService.execute(request);
    }

    @GetMapping
    public CommonApiResponse get() {
        return bookGetService.execute();
    }

    @GetMapping("/{bookId}")
    public CommonApiResponse getbody(@PathVariable Long bookId) {
        return bodyGetService.execute(bookId);
    }

    @PatchMapping
    public CommonApiResponse update(@Valid @RequestBody BookUpdateRequest request) {
        return bookUpdateService.execute(request);
    }
}
