package shop.mtcoding.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/depositForm")
    public String depositForm() {
        return "account/depositForm";
    }

    @GetMapping("/saveForm")
    public String saveForm() {
        return "account/saveForm";
    }

    @GetMapping("/transferForm")
    public String transferForm() {
        return "account/transferForm";
    }

    @GetMapping("/withdrawForm")
    public String withdrawForm() {
        return "account/withdrawForm";
    }

}
