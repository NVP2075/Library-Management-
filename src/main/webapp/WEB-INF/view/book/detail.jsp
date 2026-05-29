<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${book.title}</title>
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
  .info-item span  { font-size:14px; font-weight:500; }
  h3 { color:#1e3a5f; margin-bottom:14px; font-size:16px; }
  table { width:100%; border-collapse:collapse; }
  th { background:#1e3a5f; color:#fff; padding:10px 12px; font-size:12px; text-align:left; }
  td { padding:10px 12px; border-bottom:1px solid #eef; font-size:13px; }
  tr:last-child td { border-bottom:none; }
  .badge { padding:3px 10px; border-radius:12px; font-size:11px; font-weight:600; }
  .badge-success { background:#d4edda; color:#155724; }
  .badge-warning { background:#fff3cd; color:#856404; }
  .badge-danger  { background:#f8d7da; color:#721c24; }
  .add-item-form { display:flex; gap:10px; flex-wrap:wrap; margin-top:16px; align-items:flex-end; }
  .add-item-form .fg { display:flex; flex-direction:column; gap:4px; }
  .add-item-form label { font-size:12px; font-weight:600; color:#555; }
  input[type=text], input[type=number], select { padding:8px 10px; border:1px solid #ccc; border-radius:6px; font-size:13px; }
  .btn { padding:8px 16px; border:none; border-radius:6px; cursor:pointer; font-size:13px; text-decoration:none; display:inline-block; }
  .btn-success { background:#27ae60; color:#fff; }
  .btn-primary { background:#2e6da4; color:#fff; }
  .btn:hover { opacity:.88; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <a href="BookServlet?action=list" class="back">&#8592; Danh sách sách</a>

  <div class="card">
    <h2>${book.title}</h2>
    <div class="info-grid">
      <div class="info-item"><label>Mô Tả</label><span>${book.description}</span></div>
       <div class="info-item"><label>Thể Loại</label><span>${book.cate.getName()}</span></div>
      <div class="info-item"><label>Tác Giả</label><span>${book.auth.getName()}</span></div>
      <div class="info-item"><label>Số Lượng</label><span>${book.book_copy_list.size()}</span></div>
      <div class="info-item"><label>Còn Lại</label><span>${book.availableCount}</span></div>
    </div>
  </div>

  <div class="card">
    <h3>&#128218; Danh Sách Sách (Số lượng)</h3>
    <c:choose>
      <c:when test="${empty book.book_copy_list}">
        <p style="color:#888;font-size:13px;">Chưa có bản sao nào.</p>
      </c:when>
      <c:otherwise>
        <table>
          <tr><th>ID</th><th>Barcode</th><th>Trạng Thái</th></tr>
          <c:forEach items="${book.book_copy_list}" var="bi">
            <tr>
              <td>${bi.id}</td>
              <td>${bi.barcode}</td>
              <td>
                <c:choose>
                  <c:when test="${bi.status == 'AVAILABLE'}"><span class="badge badge-success">Available</span></c:when>
                  <c:when test="${bi.status == 'BORROWED'}"><span class="badge badge-warning">Borrowed</span></c:when>
                  <c:otherwise><span class="badge badge-danger">${bi.status}</span></c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:otherwise>
    </c:choose>

    <form action="BookServlet" method="post" class="add-item-form" style="margin-top:20px;border-top:1px solid #eee;padding-top:16px;">
      <input type="hidden" name="action" value="addItem"/>
      <input type="hidden" name="bookId" value="${book.id}"/>
      <div class="fg"><label>Barcode</label><input type="text" name="barcode" required placeholder="BI-0001"/></div>
      <input type="hidden" name="status" value="AVAILABLE"}"/>
      <div class="fg">
      </div>
      <button type="submit" class="btn btn-success">+ Thêm Bản Sao</button>
    </form>
  </div>
</div>
</body>
</html>
