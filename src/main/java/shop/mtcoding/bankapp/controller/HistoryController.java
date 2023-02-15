package shop.mtcoding.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    @GetMapping("/")
    public String main() {
        return "history/main";
    }

    @GetMapping("/detail")
    public String detail() {
        return "history/detail";
    }
}
