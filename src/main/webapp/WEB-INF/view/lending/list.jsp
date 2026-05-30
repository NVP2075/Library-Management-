<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${empty pageTitle ? 'Đang Mượn' : pageTitle}</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; color:#222; }
  .container { padding:28px 32px; }
  h2 { margin-bottom:20px; color:#1e3a5f; }
  .tabs { display:flex; gap:8px; margin-bottom:20px; }
  .tab { padding:8px 18px; border-radius:6px; text-decoration:none; font-size:13px; font-weight:500; background:#e0e7ef; color:#1e3a5f; }
  .tab.active, .tab:hover { background:#2e6da4; color:#fff; }
  table { width:100%; border-collapse:collapse; background:#fff; border-radius:10px; overflow:hidden; box-shadow:0 2px 8px rgba(0,0,0,.07); }
  th { background:#1e3a5f; color:#fff; padding:12px 14px; text-align:center; font-size:13px; }
  td { padding:11px 14px; border-bottom:1px solid #eef; font-size:13px; }
  tr:last-child td { border-bottom:none; }
  tr:hover td { background:#f5f8ff; }
  .badge { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
  .badge-success { background:#d4edda; color:#155724; }
  .badge-warning { background:#fff3cd; color:#856404; }
  .badge-danger  { background:#f8d7da; color:#721c24; }
  .btn { padding:7px 14px; border:none; border-radius:6px; cursor:pointer; font-size:12px; text-decoration:none; display:inline-block; }
  .btn-danger { background:#e74c3c; color:#fff; }
  .btn-success { background:#27ae60; color:#fff; }
  .btn:hover { opacity:.88; }
  .empty { text-align:center; padding:40px; color:#888; }
  .overdue-row td { background:#fff5f5; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#128221; ${empty pageTitle ? 'Đang Mượn' : pageTitle}</h2>

  <div class="tabs">
    <a href="LendingServlet?action=list"     class="tab">Đang Mượn</a>
    <a href="LendingServlet?action=overdue"  class="tab">Quá Hạn</a>
    <a href="LendingServlet?action=history"  class="tab">Toàn Bộ</a>
    <a href="LendingServlet?action=checkoutForm" class="tab">+ Mượn Sách</a>
  </div>

  <c:choose>
    <c:when test="${empty lendings}">
      <div class="empty">Không có dữ liệu.</div>
    </c:when>
    <c:otherwise>
      <table>
        <tr align="center">
          <th>Mã Mượn</th><th>Mã sách</th><th>Tên Sách</th><th>Barcode</th>
          <th>Tên Người Mượn</th><th>Ngày Mượn</th><th>Hạn Trả</th><th>Ngày Trả</th><th>Trạng Thái</th>
        </tr>
        <c:forEach items="${lendings}" var="l">
        	<c:forEach items="${l.borrow_ticket_detail_list}" var="btdl">
		          <fmt:formatDate value="${l.borrow_date}" pattern="dd/MM/yyyy" var="created"/>
		          <fmt:formatDate value="${btdl.due_date}"      pattern="dd/MM/yyyy" var="due"/>
		          <fmt:formatDate value="${btdl.return_date}"   pattern="dd/MM/yyyy" var="returned"/>
		          <tr align="center" class="${l.overDue() ? 'overdue-row' : ''}">
		            <td>${l.id}</td>
		            <td>${btdl.book_copy.id}</td>
		            <td>${btdl.book_copy.book.title}</td>
		             <td>${btdl.book_copy.barcode}</td>
		            <td>${l.reader.name}</td>
		            <td>${created}</td>
		            <td><c:choose>
		              <c:when test="${l.overDue()}"><strong style="color:#c0392b">${due}</strong></c:when>
		              <c:otherwise>${due}</c:otherwise>
		            </c:choose></td>
		            <td>${empty returned ? '-' : returned}</td>
		            <td>
		              <c:choose>
		                <c:when test="${l.status == 'RETURNED'}"><span class="badge badge-success">Đã Trả</span></c:when>
		                <c:when test="${btdl.overDue() }"><span class="badge badge-danger">Quá Hạn</span></c:when>
		                <c:otherwise><span class="badge badge-warning">Đang Mượn</span></c:otherwise>
		              </c:choose>
		            </td>
		            <td>
		              <c:if test="${l.status == 'ACTIVE' || l.status == 'OVERDUE'}">
		                <a href="LendingServlet?action=return&id=${l.id}"
		                   class="btn btn-danger"
		                   onclick="return confirm('Xác nhận trả sách?')">Trả Sách</a>
		              </c:if>
		            </td>
		          </tr>
        	</c:forEach>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
