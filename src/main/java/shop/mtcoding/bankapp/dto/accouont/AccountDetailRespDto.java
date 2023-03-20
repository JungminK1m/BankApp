package shop.mtcoding.bankapp.dto.accouont;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetailRespDto {
    private String fullname;
    private String number;
    private Long balance;
}
