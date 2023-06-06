<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<style>
  table {
      border: none; 
      border-collapse: collapse; 
      margin: auto;
      border-radius: 10px;
  }
      th { 
        color: black;
        background: lightgray;
        font-family: '맑은 고딕';
        font-weight: bold;
        font-size:15px;
      }
      td { 
        background: limegreen;
        color: black;
        text-align: center;
        padding: 15px;
        border: 2px solid lightgray;
        font-family: '맑은 고딕';
        font-weight: bold;
        font-size:15px;
        }
        h3{
        text-align:center;
        font-size:20px;}
</style>
<meta charset="UTF-8">
<title>방명록 정보</title>
</head>
<body>
<h3>방명록 목록</h3>
<table border="1">
   <tr><th>번호</th><th>작성자</th><th>이메일</th><th>작성일</th><th>제목</th></tr>
    <c:forEach items="${profiles}" var="p">
        <tr><td>${p.id}</td><td>${p.username}</td><td>${p.email}</td><td>${p.date}</td><td>${p.title}</td></tr>
    </c:forEach>
</table>
<!--
<h2>학생 추가</h2>
<form method="post" action="/profileControl?action=insert">
    <label>작성자</label>
    <input type="text" name="username"><br>
    <label>이메일</label>
    <input type="text" name="email"><br>
    <label>제목</label>
    <input type="text" name="title"><br>
    <label>비밀번호</label>
    <input type="text" name="password"><br>
    <input type="text" name="context" size="30"><br>
    <button type="submit">입력</button>
    <button type="cancel">취소</button>
    <button type="golist" formaction="* 리스트로 가기">등록</button> 
</form> -->
[<a href="/jwbook/profileControl">새로고침</a>]
</body>
