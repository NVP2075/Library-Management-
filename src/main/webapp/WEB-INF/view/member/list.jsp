<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thành Viên</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; color:#222; }
  .container { padding:28px 32px; }
  h2 { margin-bottom:20px; color:#1e3a5f; }
  .toolbar { display:flex; gap:12px; margin-bottom:20px; align-items:center; flex-wrap:wrap; }
  .toolbar form { display:flex; gap:8px; }
  input[type=text] { padding:8px 12px; border:1px solid #ccc; border-radius:6px; font-size:14px; width:220px; }
  .btn { padding:8px 16px; border:none; border-radius:6px; cursor:pointer; font-size:14px; text-decoration:none; display:inline-block; }
  .btn-primary { background:#2e6da4; color:#fff; }
  .btn-success { background:#27ae60; color:#fff; }
  .btn-danger  { background:#e74c3c; color:#fff; }
  .btn-info    { background:#2980b9; color:#fff; }
  .btn:hover { opacity:.88; }
  table { width:100%; border-collapse:collapse; background:#fff; border-radius:10px; overflow:hidden; box-shadow:0 2px 8px rgba(0,0,0,.07); }
  th { background:#1e3a5f; color:#fff; padding:12px 14px; text-align:center; font-size:13px; }
  td { padding:11px 14px; border-bottom:1px solid #eef; font-size:13px; }
  tr:last-child td { border-bottom:none; }
  tr:hover td { background:#f5f8ff; }
  .badge { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
  .badge-success { background:#d4edda; color:#155724; }
  .badge-danger  { background:#f8d7da; color:#721c24; }
  .badge-warning { background:#fff3cd; color:#856404; }
  .empty { text-align:center; padding:40px; color:#888; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#128101; Danh Sách Thành Viên</h2>
  <div class="toolbar">
    <form action="MemberServlet" method="get">
      <input type="hidden" name="action" value="list"/>
      <input type="text" name="keyword" placeholder="Tìm theo tên..." value="${keyword}"/>
      <button type="submit" class="btn btn-primary">Tìm</button>
    </form>
    <a href="MemberServlet?action=list" class="btn btn-info">Tất Cả</a>
    <a href="MemberServlet?action=addForm" class="btn btn-success">+ Thêm Thành Viên</a>
  </div>

  <c:choose>
    <c:when test="${empty members}">
      <div class="empty">Không có thành viên nào.</div>
    </c:when>
    <c:otherwise>
      <table>
        <tr>
          <th>ID</th><th>Họ Tên</th><th>Email</th><th>SĐT</th>
          <th>Địa chỉ</th><th>Số Sách Đã Mượn</th><th>Trạng Thái Tài Khoản</th>
        </tr>
        <c:forEach items="${members}" var="m">
          <tr align="center">
            <td>${m.id}</td>
            <td><strong>${m.name }</strong></td>
            <td>${m.email }</td>
            <td>${m.phone }</td>
            <td>${m.address }</td>
            <td>${m.totalBookBorrowed }/5</td>
            <td>
              <c:choose>
                <c:when test="${m.account_status == 'ACTIVE	'}"><span class="badge badge-success">Active</span></c:when>
                <c:when test="${m.account_status == 'BLOCKED'}"><span class="badge badge-danger">Blocked</span></c:when>
                <c:otherwise><span class="badge badge-warning">${m.account_status}</span></c:otherwise>
              </c:choose>
            </td>
            <td style="display:flex;gap:6px;">
              <a href="MemberServlet?action=detail&id=${m.id}" class="btn btn-info">Chi Tiết</a>
              <a href="MemberServlet?action=delete&id=${m.id}"
                 class="btn btn-danger"
                 onclick="return confirm('Xóa thành viên này?')">Xóa</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
