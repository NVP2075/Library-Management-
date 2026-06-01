<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${member.name}</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; color:#222; }
  .container { padding:28px 32px; }
  .back { color:#2e6da4; text-decoration:none; font-size:13px; display:inline-block; margin-bottom:16px; }
  .card { background:#fff; border-radius:12px; padding:24px 28px; box-shadow:0 2px 8px rgba(0,0,0,.07); margin-bottom:24px; }
  h2 { color:#1e3a5f; font-size:22px; margin-bottom:4px; }
  .meta { color:#888; font-size:13px; margin-bottom:16px; }
  .info-grid { display:grid; grid-template-columns:1fr 1fr; gap:12px 24px; }
  .info-item label { font-size:12px; color:#888; display:block; }
  .info-item span { font-size:14px; font-weight:500; }
  h3 { color:#1e3a5f; margin-bottom:14px; font-size:16px; }
  table { width:100%; border-collapse:collapse; }
  th { background:#1e3a5f; color:#fff; padding:10px 12px; font-size:12px; text-align:center; }
  td { padding:10px 12px; border-bottom:1px solid #eef; font-size:13px; }
  tr:last-child td { border-bottom:none; }
  .badge { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
  .badge-success { background:#d4edda; color:#155724; }
  .badge-warning { background:#fff3cd; color:#856404; }
  .badge-danger  { background:#f8d7da; color:#721c24; }
  .badge-info    { background:#cce5ff; color:#004085; }
  .btn { padding:7px 14px; border:none; border-radius:6px; cursor:pointer; font-size:12px; text-decoration:none; display:inline-block; }
  .btn-danger { background:#e74c3c; color:#fff; }
  .btn:hover { opacity:.88; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <a href="MemberServlet?action=list" class="back">&#8592; Danh sách thành viên</a>

  <div class="card">
    <h2>${member.name}</h2>
    <p class="meta">ID: ${member.id}</p>
    <div class="info-grid">
      <div class="info-item"><label>Email</label><span>${member.email}</span></div>
      <div class="info-item"><label>SĐT</label><span>${member.phone}</span></div>
      <div class="info-item"><label>Địa Chỉ</label><span>${member.address}</span></div>
      <div class="info-item">
      <div class="info-item"><label>Đang Mượn</label><span>${member.totalBookBorrowed} / 5</span></div>
      <div class="info-item">
        <label>Trạng Thái</label>
        <span>
          <c:choose>
            <c:when test="${member.account_status == 'ACTIVE'}"><span class="badge badge-success">Active</span></c:when>
            <c:otherwise><span class="badge badge-danger">${member.account_status}</span></c:otherwise>
          </c:choose>
        </span>
      </div>
    </div>
  </div>

  <div class="card">
    <h3>&#128221; Lịch Sử Mượn Sách</h3>
    <c:choose>
      <c:when test="${empty lendings}">
        <p style="color:#888;font-size:13px;">Chưa có lịch sử mượn.</p>
      </c:when>
      <c:otherwise>
        <table>
          <tr align="center">
            <th>ID</th><th>Thủ thư</th><th>Ngày Mượn</th>
            <th>Trạng Thái</th><th></th>
          </tr>
          <c:forEach items="${lendings}" var="l">
          	<tr align="center">
	          	<fmt:formatDate value="${l.borrow_date }" pattern="dd/MM/yyyy" var="borrDate"/>
		        <fmt:formatDate value="${detailList.due_date}" pattern="dd/MM/yyyy" var="due"/>
		        <fmt:formatDate value="${detailList.return_date}" pattern="dd/MM/yyyy" var="returned"/>
	          	<td>${l.id}</td>
		        <td>${l.staff.name }</td>
		        <td>${borrDate }</td>
		        <td>
		        	<c:choose>
				       	<c:when test="${l.status == 'RETURNED'}"><span class="badge badge-success">Đã Trả</span></c:when>
				        <c:when test="${detailList.overDue()}"><span class="badge badge-danger">Quá Hạn</span></c:when>
				        <c:otherwise><span class="badge badge-warning">Đang Mượn</span></c:otherwise>
		       		</c:choose>
		    	 </td>           
		              <td>
		                <c:if test="${l.status == 'ACTIVE' || l.status == 'OVERDUE'	}">
		                  <a href="LendingServlet?action=return&id=${l.id}"
		                     class="btn btn-danger"
		                     onclick="return confirm('Xác nhận trả sách?')">Trả Sách</a>
		                </c:if>
		              </td>
		            </tr>
          </c:forEach>
        </table>
      </c:otherwise>
    </c:choose>
  </div>
</div>
</div>
</body>
</html>
