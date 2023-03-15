package shop.mtcoding.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.bankapp.dto.accouont.AccountSaveReqDto;
import shop.mtcoding.bankapp.dto.accouont.AccountWithdrawReqDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.account.Account;
import shop.mtcoding.bankapp.model.account.AccountRepository;
import shop.mtcoding.bankapp.model.history.History;
import shop.mtcoding.bankapp.model.history.HistoryRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional
    public void 계좌생성(AccountSaveReqDto accountSaveReqDto, int principalId) {
        Account account = accountSaveReqDto.toEntity(principalId);
        accountRepository.insert(account);
    }

    @Transactional
    public int 계좌출금(AccountWithdrawReqDto accountWithdrawReqDto) {

        // 1. 계좌 존재 여부
        Account accountPS = accountRepository.findByNumber(accountWithdrawReqDto.getWAccountNumber());
        if (accountPS == null) {
            throw new CustomException("계좌가 존재하지 않습니다", HttpStatus.BAD_REQUEST);
        }
        // 2. 계좌 패스워드 확인 (accountPS와 입력 받은 패스워드를 비교)
        accountPS.CheckPassword(accountWithdrawReqDto.getWAccountPassword());

        // 3. 잔액확인
        accountPS.CheckBalance(accountWithdrawReqDto.getAmount());

        // 4. 출금(balance를 - 하는 것)
        accountPS.Withdraw(accountWithdrawReqDto.getAmount());
        accountRepository.updateById(accountPS);

        // 5. 히스토리 남기기 (거래내역) - insert 할 거임
        // history 객체를 만들어야 함
        // 어디에 있는 정보들로 넣어줄까? => 위에 있는 정보로!
        History history = new History(); // PS 안붙이는 이유 : DB에서 조회한 것이 아니라 내가 만든 것
        history.setAmount(accountWithdrawReqDto.getAmount()); // 얼마가 들아왔는지
        history.setWAccountId(accountPS.getId()); // 출금계좌 ID
        history.setDAccountId(null); // 입금계좌 ID
        history.setWBalance(accountPS.getBalance()); // 출금계좌의 잔액
        history.setDBalance(null); // 입금계좌의 잔액
        ; // 입금계좌의 잔액
        historyRepository.insert(history);

        // 6. 해당 계좌의 id를 return - 디테일 화면으로 돌아가야 하니까 => id니까 리턴 타입이 int
        return accountPS.getId();
    }

}
