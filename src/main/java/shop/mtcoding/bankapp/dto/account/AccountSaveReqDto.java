package shop.mtcoding.bankapp.dto.account;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bankapp.model.account.Account;

@Getter
@Setter
public class AccountSaveReqDto {
    private String number;
    private String password;

    public Account toEntity(int principalId) {
        Account account = new Account();
        account.setNumber(number);
        account.setPassword(password);
        account.setUserId(principalId);
        account.setBalance(1000L);
        return account;
    }
}
