package shop.mtcoding.bankapp.model.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import shop.mtcoding.bankapp.dto.account.AccountDetailRespDto;

@Mapper
@Repository
public interface AccountRepository {
    public int insert(Account account);

    public int updateById(Account account);

    public int deleteById(int id);

    public List<Account> findAll();

    public List<Account> findByUserId(Integer id);

    public Account findById(int id);

    public Account findByNumber(String number);

    public AccountDetailRespDto findByIdWithUser(int id);

}