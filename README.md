# 🏛 Bank Application 💰
[프로젝트 전체 내용 정리📝](https://www.notion.so/Bank-Application-6cdfc78dd0244a6b9d3108265e76e8d0)

# 화면 구현 📺
- (메인 화면) 로그인 페이지
- 회원가입 페이지
- 출금하기 (ATM)
- 입금하기 (ATM)
- (로그인 시 메인 페이지) 계좌 목록 페이지
- (로그인 시) 계좌 상세보기 페이지
- (로그인 시) 계좌 생성 페이지
- (로그인 시) 이체하기

|메인 화면 - 로그인X|메인 화면 - 로그인O - 계좌 목록 페이지|
|:---:|:---:|
|![image](https://github.com/JungminK1m/BankApp/assets/118741874/adc29311-1fd9-4705-8cf8-ea20848d18ea)|![image](https://github.com/JungminK1m/BankApp/assets/118741874/fdf1454e-2755-4602-a422-540818d4cd2c)|

|회원가입 페이지|
|:---:|
|![image](https://github.com/JungminK1m/BankApp/assets/118741874/7edebc68-d1d4-43f1-8b1b-58e7c61977f0)|

|계좌 상세보기|
|:---:|
|![image](https://github.com/JungminK1m/BankApp/assets/118741874/fee152e0-3fb3-4230-875c-48990d10ac31)|

|ATM 출금하기|ATM 입금하기|
|:---:|:---:|
|![image](https://github.com/JungminK1m/BankApp/assets/118741874/89e1d706-5239-4f68-a98d-d80ac6d34376)|![image](https://github.com/JungminK1m/BankApp/assets/118741874/89e1d706-5239-4f68-a98d-d80ac6d34376)|

|계좌 생성 페이지|계좌 이체 페이지|
|:---:|:---:|
|![image](https://github.com/JungminK1m/BankApp/assets/118741874/689e4d57-5c8c-45fc-aa02-a607c5db0673)|![image](https://github.com/JungminK1m/BankApp/assets/118741874/197db7ac-8451-493e-a7a1-46f2b49eb305)|

  <br/>
  
  
# 사용기술 🧪
![Springboot](https://img.shields.io/badge/-Springboot-6DB33F)
![Java](https://img.shields.io/badge/-Java-F09820)
![CSS](https://img.shields.io/badge/-CSS-1572B6)
![HTML](https://img.shields.io/badge/-HTML-E34F26)
![JS](https://img.shields.io/badge/-JavaScript-F7DF1E)
![Bootstrap](https://img.shields.io/badge/-Bootstrap-7952B3)
![MyBatis](https://img.shields.io/badge/-MyBatis-B10000)
![H2](https://img.shields.io/badge/-H2Console-41BDF5)  
  <br/>
  
 
# 기능구현 🔧
- 상세보기 페이지에서 구분 값에 따른 다른 결과 출력

![image](https://github.com/JungminK1m/BankApp/assets/118741874/0e86234c-7d8c-4312-b020-b4de2393810b)
![image](https://github.com/JungminK1m/BankApp/assets/118741874/161395b6-e06e-4a66-be77-cc5620c00f1c)
![image](https://github.com/JungminK1m/BankApp/assets/118741874/5bb8fefd-03a4-4857-aff3-1b3e51a5b145)

```html
<select id="findByGubun" resultType="shop.mtcoding.bankapp.dto.history.HistoryRespDto">
    <if test="gubun == 'withdraw'">
        SELECT ht.id, ht.amount, ht.w_balance balance, wat.number sender, 'ATM' receiver, ht.created_at
        FROM history_tb ht
        INNER JOIN account_tb wat
        ON ht.w_account_id = wat.id
        WHERE ht.w_account_id = #{accountId}
    </if>

    <if test="gubun == 'deposit'">
        SELECT ht.id, ht.amount, ht.d_balance balance, 'ATM' sender, dat.number receiver, ht.created_at
        FROM history_tb ht
        INNER JOIN account_tb dat
        ON ht.d_account_id = dat.id
        WHERE ht.d_account_id = #{accountId}
    </if>

    <if test="gubun == 'all'">
        SELECT
        ht.id,
        ht.amount,
        case when ht.w_account_id =  #{accountId} then ht.w_balance
        when ht.d_account_id =  #{accountId} then ht.d_balance
        end as balance,
        nvl(wat.number, 'ATM') sender,
        nvl(dat.number, 'ATM') receiver,
        ht.created_at
        FROM history_tb ht
        LEFT OUTER JOIN account_tb wat
        ON ht.w_account_id = wat.id
        LEFT OUTER JOIN account_tb dat
        ON ht.d_account_id = dat.id
        WHERE ht.w_account_id =  #{accountId} or ht.d_account_id =  #{accountId}
    </if>
</select>
```
  <br/>
  

# 테이블 생성 📁
```sql
CREATE TABLE user_tb(
    id int auto_increment primary key,
    username varchar unique not null,
    password varchar not null,
    fullname varchar not null,
    created_at timestamp not null
);
CREATE TABLE account_tb(
    id int auto_increment primary key,
    number varchar unique not null,
    password varchar not null,
    balance bigint not null,
    user_id int,
    created_at timestamp not null
);
CREATE TABLE history_tb(
    id int auto_increment primary key,
    amount bigint not null,
    w_balance bigint,
    d_balance bigint,
    w_account_id int,
    d_account_id int,
    created_at timestamp not null
);
```
# 더미 데이터 📰
```sql
INSERT INTO user_tb(username, password, fullname, created_at) values('ssar', '1234', '쌀', now());
INSERT INTO user_tb(username, password, fullname, created_at) values('cos', '1234', '코스', now());

INSERT INTO account_tb(number, password, balance, user_id, created_at) values('1111', '1234', 900, 1, now());
INSERT INTO account_tb(number, password, balance, user_id, created_at) values('2222', '1234', 1100, 2, now());
INSERT INTO account_tb(number, password, balance, user_id, created_at) values('3333', '1234', 1000, 1, now());

INSERT INTO history_tb(amount, w_balance, d_balance, w_account_id, d_account_id, created_at) values(100, 900, 1100, 1, 2, now());
INSERT INTO history_tb(amount, w_balance, d_balance, w_account_id, d_account_id, created_at) values(100, 800, null, 1, null, now());
INSERT INTO history_tb(amount, w_balance, d_balance, w_account_id, d_account_id, created_at) values(100, null, 900, null, 1, now());
```


