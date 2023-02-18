package shop.mtcoding.bankapp.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bankapp.model.user.User;

@Getter
@Setter
public class LoginReqDto {
    private String username;
    private String password;

}
