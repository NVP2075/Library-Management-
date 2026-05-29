<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Kết Quả Trả Sách</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; }
  .container { max-width:480px; margin:60px auto; background:#fff; padding:40px; border-radius:14px; box-shadow:0 4px 20px rgba(0,0,0,.1); text-align:center; }
  .icon { font-size:56px; margin-bottom:16px; }
  h2 { color:#1e3a5f; margin-bottom:8px; }
  .fine-box { background:#fff3cd; border:1px solid #ffc107; border-radius:8px; padding:16px 20px; margin:20px 0; }
  .fine-amount { font-size:28px; font-weight:700; color:#c0392b; }
  .fine-label  { font-size:13px; color:#856404; }
  .ok-box { background:#d4edda; border:1px solid #28a745; border-radius:8px; padding:16px 20px; margin:20px 0; color:#155724; }
  .actions { display:flex; gap:12px; justify-content:center; margin-top:24px; }
  .btn { padding:10px 24px; border:none; border-radius:6px; cursor:pointer; font-size:14px; text-decoration:none; display:inline-block; }
  .btn-primary { background:#2e6da4; color:#fff; }
  .btn-secondary { background:#aaa; color:#fff; }
  .btn:hover { opacity:.88; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <c:choose>
    <c:when test="${fine < 0}">
      <div class="icon">&#10060;</div>
      <h2>Lỗi</h2>
      <p style="color:#888;font-size:14px;margin-top:8px;">Không tìm thấy bản ghi mượn hoặc sách đã được trả.</p>
    </c:when>
    <c:when test="${fine > 0}">
      <div class="icon">&#128203;</div>
      <h2>Trả Sách Thành Công</h2>
      <p style="color:#888;font-size:13px;margin-top:4px;">Phiếu mượn #${lendingId}</p>
      <div class="fine-box">
        <div class="fine-label">Tiền phạt trả trễ</div>
        <div class="fine-amount"><fmt:formatNumber value="${fine}" pattern="#,###"/> VNĐ</div>
      </div>
    </c:when>
    <c:otherwise>
      <div class="icon">&#9989;</div>
      <h2>Trả Sách Thành Công</h2>
      <p style="color:#888;font-size:13px;margin-top:4px;">Phiếu mượn #${lendingId}</p>
      <div class="ok-box">Trả đúng hạn. Không có tiền phạt.</div>
    </c:otherwise>
  </c:choose>

  <div class="actions">
    <a href="LendingServlet?action=list" class="btn btn-primary">Về Danh Sách</a>
    <a href="index.jsp" class="btn btn-secondary">Dashboard</a>
  </div>
</div>
</body>
</html>
