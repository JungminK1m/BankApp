<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%@ include file="../layout/header.jsp" %>


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
                    <c:forEach items="${accountList}" var="account">
                        <tr>
                            <td><a href="/account/${account.id}">${account.number}</a></td>
                            <td>${account.balance}원</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <%@ include file="../layout/footer.jsp" %>