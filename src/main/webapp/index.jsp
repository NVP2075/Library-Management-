<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>STU Library Management System </title>
<style>
  * { box-sizing: border-box; margin: 0; padding: 0; }
  body { font-family: 'Segoe UI', sans-serif; background: #f0f4f8; color: #222; }
  .hero { background: linear-gradient(135deg,#1e3a5f,#2e6da4); color:#fff; padding:48px 32px; text-align:center; }
  .hero h1 { font-size: 36px; margin-bottom: 8px; }
  .hero p  { font-size: 15px; opacity:.8; }
  .cards   { display:flex; flex-wrap:wrap; gap:20px; padding:32px; justify-content:center; }
  .card    { background:#fff; border-radius:12px; padding:28px 32px; width:220px;
             box-shadow:0 2px 10px rgba(0,0,0,.08); text-align:center; text-decoration:none; color:#333;
             transition:transform .15s, box-shadow .15s; }
  .card:hover { transform:translateY(-4px); box-shadow:0 8px 24px rgba(0,0,0,.13); }
  .card .icon { font-size:40px; margin-bottom:12px; }
  .card .label { font-size:16px; font-weight:600; }
  .card .sub   { font-size:12px; color:#888; margin-top:4px; }
  .card.blue   { border-top:4px solid #2e6da4; }
  .card.green  { border-top:4px solid #27ae60; }
  .card.orange { border-top:4px solid #e67e22; }
  .card.red    { border-top:4px solid #e74c3c; }
  .card.purple { border-top:4px solid #8e44ad; }
</style>
</head>
<body>
<jsp:include page="WEB-INF/view/navbar.jsp"/>
<div class="hero">
  <h1>&#128218; STU Library Management System</h1>
  <p>Quản lý sách, thành viên và lịch sử mượn trả</p>
</div>
<div class="cards">
  <a href="BookServlet?action=list" class="card blue">
    <div class="icon">&#128218;</div>
    <div class="label">Danh Sách Sách</div>
    <div class="sub">Xem &amp; tìm kiếm sách</div>
  </a>
  <a href="BookServlet?action=addForm" class="card blue">
    <div class="icon">&#10133;</div>
    <div class="label">Thêm Sách Mới</div>
    <div class="sub">Thêm đầu sách vào thư viện</div>
  </a>
  <a href="MemberServlet?action=list" class="card green">
    <div class="icon">&#128101;</div>
    <div class="label">Thành Viên</div>
    <div class="sub">Quản lý hội viên</div>
  </a>
  <a href="MemberServlet?action=addForm" class="card green">
    <div class="icon">&#128100;</div>
    <div class="label">Thêm Thành Viên</div>
    <div class="sub">Đăng ký thành viên mới</div>
  </a>
  <a href="LendingServlet?action=checkoutForm" class="card orange">
    <div class="icon">&#128221;</div>
    <div class="label">Mượn Sách</div>
    <div class="sub">Cho thành viên mượn sách</div>
  </a>
  <a href="LendingServlet?action=list" class="card orange">
    <div class="icon">&#9200;</div>
    <div class="label">Đang Mượn</div>
    <div class="sub">Theo dõi sách đang mượn</div>
  </a>
  <a href="LendingServlet?action=overdue" class="card red">
    <div class="icon">&#9888;</div>
    <div class="label">Quá Hạn</div>
    <div class="sub">Sách chưa trả đúng hạn</div>
  </a>
  <a href="AuthorServlet?action=list" class="card purple">
    <div class="icon">&#9997;</div>
    <div class="label">Tác Giả</div>
    <div class="sub">Quản lý danh sách tác giả</div>
  </a>
</div>
</body>
</html>
