<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Cho Mượn Sách</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; }
  .container { max-width:580px; margin:32px auto; background:#fff; padding:32px; border-radius:12px; box-shadow:0 2px 12px rgba(0,0,0,.09); }
  h2 { margin-bottom:24px; color:#1e3a5f; }
  .form-group { margin-bottom:16px; }
  label { display:block; font-size:13px; font-weight:600; margin-bottom:5px; color:#555; }
  select { width:100%; padding:9px 12px; border:1px solid #ccc; border-radius:6px; font-size:14px; }
  select:focus { outline:none; border-color:#2e6da4; box-shadow:0 0 0 2px rgba(46,109,164,.15); }
  .error { background:#fff0f0; color:#c0392b; border:1px solid #f5c6cb; padding:10px 14px; border-radius:6px; margin-bottom:16px; font-size:13px; }
  .info-box { background:#eaf4ff; border:1px solid #b8daff; border-radius:6px; padding:10px 14px; font-size:12px; color:#1a5276; margin-bottom:16px; }
  .actions { display:flex; gap:12px; margin-top:24px; }
  .btn { padding:10px 22px; border:none; border-radius:6px; cursor:pointer; font-size:14px; text-decoration:none; display:inline-block; }
  .btn-primary { background:#2e6da4; color:#fff; }
  .btn-secondary { background:#aaa; color:#fff; }
  .btn:hover { opacity:.88; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#128221; Cho Mượn Sách</h2>

  <c:if test="${not empty error}">
    <div class="error">&#9888; ${error}</div>
  </c:if>

  <div class="info-box">
    Mỗi thành viên được mượn tối đa <strong>5 cuốn</strong> cùng lúc. Thời hạn mượn <strong>10 ngày</strong>.
  </div>

  <form action="LendingServlet" method="post">
    <input type="hidden" name="action" value="checkout"/>

    <div class="form-group">
      <label>Thành Viên *</label>
      <select name="memberId" required>
        <option value="">-- Chọn thành viên --</option>
        <c:forEach items="${members}" var="m">
          <option value="${m.id}">${m.name} (Đang mượn: ${m.totalBookBorrowed}/5)</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label>Sách*</label>
      <select name="bookItemId" required>
        <option value="">-- Chọn sách --</option>
        <c:forEach items="${books}" var="b">
          <c:forEach items="${b.book_copy_list}" var="bi">
            <c:if test="${bi.status == 'AVAILABLE'}">
              <option value="${bi.id}">[${bi.barcode}] ${b.title} — ${bi.status}</option>
            </c:if>
          </c:forEach>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label>Thủ thư*</label>
      <select name="staffId" required>
        <option value="">-- Người cho mượn --</option>
        <c:forEach items="${staffs}" var="staff">
            <c:if test="${staff.role == 'LIBRARIAN'}">
              <option value="${staff.id}">${staff.name}</option>
            </c:if>
          </c:forEach>
      </select>
    </div>

    <div class="actions">
      <button type="submit" class="btn btn-primary">Xác Nhận Mượn</button>
      <a href="LendingServlet?action=list" class="btn btn-secondary">Hủy</a>
    </div>
  </form>
</div>
</body>
</html>
