package shop.mtcoding.bankapp.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.bankapp.dto.account.AccountDepositReqDto;
import shop.mtcoding.bankapp.dto.account.AccountDetailRespDto;
import shop.mtcoding.bankapp.dto.account.AccountSaveReqDto;
import shop.mtcoding.bankapp.dto.account.AccountTransferReqDto;
import shop.mtcoding.bankapp.dto.account.AccountWithdrawReqDto;
import shop.mtcoding.bankapp.dto.history.HistoryRespDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.account.Account;
import shop.mtcoding.bankapp.model.account.AccountRepository;
import shop.mtcoding.bankapp.model.history.HistoryRepository;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @PostMapping("/account/transfer")
    public String transfer(AccountTransferReqDto accountTransferReqDto, Model model) {
        // 1. 인증 필요
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("로그인을 먼저 해 주세요.", HttpStatus.BAD_REQUEST);
        }
        // 2. 유효성 검사 4건
        if (accountTransferReqDto.getWAccountNumber().equals(accountTransferReqDto.getDAccountNumber())) {
            throw new CustomException("출금계좌와 입금계좌가 동일할 수 없습니다", HttpStatus.BAD_REQUEST);
        }

        if (accountTransferReqDto.getAmount() == null) {
            throw new CustomException("Amount를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountTransferReqDto.getAmount().longValue() <= 0) {
            throw new CustomException("이체금액이 0원 이하일 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        if (accountTransferReqDto.getDAccountNumber() == null || accountTransferReqDto.getDAccountNumber().isEmpty()) {
            throw new CustomException("DAccountNumber(입금계좌번호) 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        if (accountTransferReqDto.getWAccountNumber() == null || accountTransferReqDto.getWAccountNumber().isEmpty()) {
            throw new CustomException("WAccountNumber(출금계좌번호) 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountTransferReqDto.getWAccountPassword() == null
                || accountTransferReqDto.getWAccountPassword().isEmpty()) {
            throw new CustomException("WAccountPassword(출금계좌 비밀번호) 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        // 3. 서비스 호출
        // accountTransferReqDto 넘기고
        // principal.getId() - 현재 로그인 한 사람이 누군지
        int accountId = accountService.이체하기(accountTransferReqDto, principal.getId());

        // 4. 상세보기 페이지로 가기
        return "redirect:/account/" + accountId;
    }

    @PostMapping("/account/deposit")
    public String deposit(AccountDepositReqDto accountDepositReqDtp) {
        // 1. 인증체크? 필요없음 왜냐!! ATM기로 할거니까
        // 2. 유효성 검사? 해야함 왜?! POST니까
        // 유효성 검사는 2개 해야함. DTO가 2개니까!
        if (accountDepositReqDtp.getAmount() == null) {
            throw new CustomException("Amount를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountDepositReqDtp.getAmount().longValue() <= 0) {
            throw new CustomException("입금액이 0원 이하일 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        if (accountDepositReqDtp.getDAccountNumber() == null || accountDepositReqDtp.getDAccountNumber().isEmpty()) {
            throw new CustomException("DAccountNumber(입금계좌번호) 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        accountService.입금하기(accountDepositReqDtp);

        return "redirect:/";
    }

    @PostMapping("/account/withdraw")
    public String withdraw(AccountWithdrawReqDto accountWithdrawReqDto) {
        // 1. 인증체크? 필요없음 왜냐!! ATM기로 할거니까

        // 2. 유효성 검사? 해야함 왜?! POST니까
        // 유효성 검사는 3개 해야함. DTO가 3개니까!
        if (accountWithdrawReqDto.getAmount() == null) {
            throw new CustomException("Amount를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountWithdrawReqDto.getAmount().longValue() <= 0) {
            throw new CustomException("출금액이 0원 이하일 수 없습니다", HttpStatus.BAD_REQUEST);
        }

        if (accountWithdrawReqDto.getWAccountNumber() == null || accountWithdrawReqDto.getWAccountNumber().isEmpty()) {
            throw new CustomException("WAccountNumber(출금계좌번호) 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountWithdrawReqDto.getWAccountPassword() == null
                || accountWithdrawReqDto.getWAccountPassword().isEmpty()) {
            throw new CustomException("WAccountPassword(출금계좌 비밀번호) 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        int accountId = accountService.계좌출금(accountWithdrawReqDto);

        return "redirect:/account/" + accountId;
    }

    @PostMapping("/account")
    public String save(AccountSaveReqDto accountSaveReqDto) {
        // 1. 인증검사
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("로그인을 먼저 해 주세요.", HttpStatus.BAD_REQUEST);
        }

        // 2. 유효성 검사
        if (accountSaveReqDto.getNumber() == null || accountSaveReqDto.getNumber().isEmpty()) {
            throw new CustomException("number을 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (accountSaveReqDto.getPassword() == null || accountSaveReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        // 3. 서비스 호출
        accountService.계좌생성(accountSaveReqDto, principal.getId());

        return "redirect:/";
    }

    @GetMapping({ "/", "/account" })
    public String main(Model model) { // model에 값을 추가하면 request에 저장된다
        // 1. 인증검사
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        List<Account> accountList = accountRepository.findByUserId(principal.getId());
        model.addAttribute("accountList", accountList);

        return "account/main";
    }

    // 계좌 상세보기
    @GetMapping("/account/{id}")
    public String detail(@PathVariable int id, @RequestParam(name = "gubun", defaultValue = "all") String gubun,
            Model model) {
        User principal = (User) session.getAttribute("principal");
        // 1. 인증체크
        if (principal == null) {
            return "redirect:/loginForm";
        }

        // 2. 레파지토리 호출
        // 메서드를 3개 쓰거나 OR MyBatis 동적 쿼리

        // 계좌 상세 정보
        AccountDetailRespDto aDto = accountRepository.findByIdWithUser(id);
        if (aDto.getUserId() != principal.getId()) {
            throw new CustomException("해당 계좌를 볼 수 있는 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        // 입출금 내역
        List<HistoryRespDto> historyDtoList = historyRepository.findByGubun(gubun, id);

        // jsp에 뿌리기 위해 model에 담자!
        model.addAttribute("aDto", aDto);
        model.addAttribute("historyDtoList", historyDtoList);

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