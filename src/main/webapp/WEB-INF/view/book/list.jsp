<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Danh Sách Sách</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; color:#222; }
  .container { padding:28px 32px; }
  h2 { margin-bottom:20px; color:#1e3a5f; font-size:22px; }
  .toolbar { display:flex; gap:12px; margin-bottom:20px; flex-wrap:wrap; align-items:center; }
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
  td { padding:11px 14px; border-bottom:1px solid #eef0f3; font-size:13px; }
  tr:last-child td { border-bottom:none; }
  tr:hover td { background:#f5f8ff; }
  .badge { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
  .badge-success { background:#d4edda; color:#155724; }
  .badge-warning { background:#fff3cd; color:#856404; }
  .badge-danger  { background:#f8d7da; color:#721c24; }
  .empty { text-align:center; padding:40px; color:#888; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#128218; Danh Sách Sách</h2>
  <div class="toolbar">
    <form action="BookServlet" method="get">
      <input type="hidden" name="action" value="list"/>
      <input type="text" name="keyword" placeholder="Tìm theo tiêu đề..." value="${keyword}"/>
      <button type="submit" class="btn btn-primary">Tìm</button>
    </form>
    <form action="BookServlet" method="get">
      <input type="hidden" name="action" value="list"/>
      <input type="text" name="subject" placeholder="Tìm theo thể loại..." value="${subject}"/>
      <button type="submit" class="btn btn-info">Lọc</button>
    </form>
    <a href="BookServlet?action=list" class="btn btn-info">Tất Cả</a>
    <a href="BookServlet?action=addForm" class="btn btn-success">+ Thêm Sách</a>
    <a href="BookServlet?action=addCateForm" class="btn btn-success">+ Thêm Thể Loại</a>
  </div>

  <c:choose>
    <c:when test="${empty books}">
      <div class="empty">Không tìm thấy sách nào.</div>
    </c:when>
    <c:otherwise>
      <table>
        <tr>
          <th>ID</th><th>Tiêu Đề</th><th>Mô Tả</th><th>Hình Ảnh</th>
          <th>Thể Loại</th><th>Tác Giả</th><th>Còn Lại</th><th></th>
        </tr>
        <c:forEach items="${books}" var="b">
          <tr align="center">
            <td>${b.id}</td>
            <td><strong>${b.title}</strong></td>
            <td>${b.description}</td>
            <td>${b.image_url}</td>
            <td>${b.cate }</td>
            <td>${b.auth.getName() }</td>
            <td>
              <c:choose>
                <c:when test="${b.availableCount > 0}">
                  <span class="badge badge-success">${b.availableCount}</span>
                </c:when>
                <c:otherwise>
                  <span class="badge badge-danger">0</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td style="display:flex;gap:6px;flex-wrap:wrap;">
              <a href="BookServlet?action=detail&id=${b.id}" class="btn btn-info">Chi Tiết</a>
              <a href="BookServlet?action=delete&id=${b.id}"
                 class="btn btn-danger"
                 onclick="return confirm('Xóa sách này?')">Xóa</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
