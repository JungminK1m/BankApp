package shop.mtcoding.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.bankapp.dto.user.JoinReqDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) { // DTO로 받는 것이 좋다. 한 방에. 물건 줄 때 가방에 담아서 주는 것처럼 , 오브젝트 안에 담아줌
        // 1. 유효성 검사 (이거보다 우선되는 것이 인증검사)
        // http 메서드 4가지중 post put
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력하세요", HttpStatus.BAD_REQUEST);
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력하세요", HttpStatus.BAD_REQUEST);
        }
        if (joinReqDto.getFullname() == null || joinReqDto.getFullname().isEmpty()) {
            throw new CustomException("fullname을 입력하세요", HttpStatus.BAD_REQUEST);
        }
        // 여기를 다 통과했다면 insert
        // 컨트롤러 검증이 다 끝나고 서비스 호출하기
        // 컨벤션 : post. put, delete 할 때만 하기 / 컨벤션이 뭐지?
        // 서비스 호출 => 회원가입();
        userService.회원가입(joinReqDto);
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

}
