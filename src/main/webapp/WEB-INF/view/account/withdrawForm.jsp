<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp" %>

  <h1>ATM 출금 페이지</h1>
    <hr>
    <form action="/account/withdraw" method="post">
        <input type="text" name="amount" placeholder="Enter 출금금액"><br>
        <input type="text" name="wAccountNumber" placeholder="Enter 출금계좌번호"><br>
        <input type="text" name="wAccountPassword" placeholder="Enter 출금계좌비밀번호"><br>
        <button>출금</button>
    </form>

<%@ include file="../layout/footer.jsp" %>