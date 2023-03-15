package shop.mtcoding.bankapp.dto.accouont;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDepositReqDtp {
    private Long amount;
    private String dAccountNumber;
}
