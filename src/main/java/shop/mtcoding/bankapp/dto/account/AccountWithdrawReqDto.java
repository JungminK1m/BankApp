package shop.mtcoding.bankapp.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountWithdrawReqDto {
    // DTO는 wrapper 클래스로 만들어야 한다. null 체크하기 위해
    private Long amount;
    private String wAccountNumber;
    private String wAccountPassword;
}
