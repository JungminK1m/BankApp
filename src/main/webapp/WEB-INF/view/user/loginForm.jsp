<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp" %>

 <h1>로그인 페이지</h1>
    <hr>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Enter username"><br>
        <input type="text" name="password" placeholder="Enter password"><br>
        <button>로그인</button>
    </form>

<%@ include file="../layout/footer.jsp" %>