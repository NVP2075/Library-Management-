<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Tác Giả</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; }
  .container { padding:28px 32px; }
  h2 { margin-bottom:20px; color:#1e3a5f; }
  .toolbar { display:flex; gap:12px; margin-bottom:20px; }
  .btn { padding:8px 16px; border:none; border-radius:6px; cursor:pointer; font-size:14px; text-decoration:none; display:inline-block; }
  .btn-success { background:#27ae60; color:#fff; }
  .btn:hover { opacity:.88; }
  table { width:100%; border-collapse:collapse; background:#fff; border-radius:10px; overflow:hidden; box-shadow:0 2px 8px rgba(0,0,0,.07); }
  th { background:#1e3a5f; color:#fff; padding:12px 14px; text-align:left; font-size:13px; }
  td { padding:11px 14px; border-bottom:1px solid #eef; font-size:13px; }
  tr:last-child td { border-bottom:none; }
  tr:hover td { background:#f5f8ff; }
  .empty { text-align:center; padding:40px; color:#888; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#9997; Danh Sách Tác Giả</h2>
  <div class="toolbar">
    <a href="AuthorServlet?action=addForm" class="btn btn-success">+ Thêm Tác Giả</a>
  </div>
  <c:choose>
    <c:when test="${empty authors}">
      <div class="empty">Chưa có tác giả nào.</div>
    </c:when>
    <c:otherwise>
      <table>
        <tr><th>ID</th><th>Họ Tên</th><th>Số Sách</th></tr>
        <c:forEach items="${authors}" var="a">
          <tr>
            <td>${a.id}</td>
            <td><strong>${a.name}</strong></td>
            <td>${a.bio }</td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
