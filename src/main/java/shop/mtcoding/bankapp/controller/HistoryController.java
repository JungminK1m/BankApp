package shop.mtcoding.bankapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.bankapp.handler.ex.CustomException;

@Controller
public class HistoryController {

    @GetMapping("/")
    public String main() {

        throw new CustomException("인증되지 않았습니다", HttpStatus.UNAUTHORIZED);
        // return "history/main";
    }

    @GetMapping("/detail")
    public String detail() {
        return "history/detail";
    }
}
