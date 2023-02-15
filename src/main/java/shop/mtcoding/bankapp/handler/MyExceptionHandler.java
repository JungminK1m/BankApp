package shop.mtcoding.bankapp.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.bankapp.handler.ex.CustomException;

@RestControllerAdvice
public class MyExceptionHandler {

    // 무조건 데이터를 응답할 거기 때문에 RestController
    // 자바 스크립트를 응답
    // 어노테이션 ()안에 타입을 적어줘야 함
    // 런타임익셉션 - 모든 익셉션이 다 여기로 올 거임

    // exception터지면 다 여기로 올 거임! 무조건 뒤로가기

    @ExceptionHandler(CustomException.class)
    public String basicException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    // Dto를 응답

}
