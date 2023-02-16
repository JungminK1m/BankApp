package shop.mtcoding.bankapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.bankapp.dto.accouont.AccountSaveReqDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;

@Controller
public class AccountController {

    @PostMapping("/account")
    public String save(AccountSaveReqDto accountSaveReqDto) {
        if (accountSaveReqDto.getNumber() == null || accountSaveReqDto.getNumber().isEmpty()) {
            throw new CustomException("number을 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountSaveReqDto.getPassword() == null || accountSaveReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        return "redirect:/";
    }

    @GetMapping({ "/", "/account" })
    public String main() {
        return "account/main";
    }

    @GetMapping("/account/{id}")
    public String detail() {
        return "account/detail";
    }

    @GetMapping("/account/saveForm")
    public String saveForm() {
        return "account/saveForm";
    }

    @GetMapping("/account/withdrawForm")
    public String withdrawForm() {
        return "account/withdrawForm";
    }

    @GetMapping("/account/depositForm")
    public String depositForm() {
        return "account/depositForm";
    }

    @GetMapping("/account/transferForm")
    public String transferForm() {
        return "account/transferForm";
    }
}