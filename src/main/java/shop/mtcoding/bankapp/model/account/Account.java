package shop.mtcoding.bankapp.model.account;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bankapp.handler.ex.CustomException;

@Setter
@Getter
public class Account {
    private Integer id;
    private String number;
    private String password;
    private Long balance;
    private Integer userId;
    private Timestamp createdAt;

    public void CheckPassword(String password) {
        if (!this.password.equals(password)) {
            throw new CustomException("비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    public void CheckBalance(long amount) {
        if (this.balance < amount) {
            throw new CustomException("잔액이 부족합니다.", HttpStatus.BAD_REQUEST);
        }
    }

    public void CheckOwner(Integer principalId) {
        if (this.userId != principalId) {
            throw new CustomException("출금계좌의 소유주가 아닙니다.", HttpStatus.FORBIDDEN);
        }
    }

    public void Withdraw(long amount) {
        this.balance = this.balance - amount;
    }

    public void Deposit(long amount) {
        this.balance = this.balance + amount;
    }

}