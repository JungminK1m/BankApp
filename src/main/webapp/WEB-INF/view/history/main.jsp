<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp" %>

  <ul>
        <li><a href="/loginForm">로그인</a></li>
        <li><a href="/joinForm">회원가입</a></li>
        <li><a href="/">로그아웃</a></li>
        <li><a href="/">계좌목록(인증)</a></li>
        <li><a href="/saveForm">계좌생성(인증)</a></li>
        <li> <a href="/withdrawForm">출금하기(인증)</a></li>
        <li><a href="/depositForm">입금하기(미인증)</a></li>
        <li><a href="/transferForm">이체하기(미인증)</a></li>
    </ul>

    <h1>메인 페이지</h1>
    <hr>
    <br>
    <table border="1">
        <thead>
            <tr>
                <th>계좌번호</th>
                <th>잔액</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1111</td>
                <td>1000원</td>
            </tr>
        </tbody>
    </table>
<%@ include file="../layout/footer.jsp" %>