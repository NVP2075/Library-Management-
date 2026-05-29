<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thêm Tác Giả</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; }
  .container { max-width:440px; margin:32px auto; background:#fff; padding:32px; border-radius:12px; box-shadow:0 2px 12px rgba(0,0,0,.09); }
  h2 { margin-bottom:24px; color:#1e3a5f; }
  .form-group { margin-bottom:16px; }
  label { display:block; font-size:13px; font-weight:600; margin-bottom:5px; color:#555; }
  input[type=text] { width:100%; padding:9px 12px; border:1px solid #ccc; border-radius:6px; font-size:14px; }
  input:focus { outline:none; border-color:#8e44ad; box-shadow:0 0 0 2px rgba(142,68,173,.15); }
  .actions { display:flex; gap:12px; margin-top:24px; }
  .btn { padding:10px 22px; border:none; border-radius:6px; cursor:pointer; font-size:14px; text-decoration:none; }
  .btn-primary { background:#8e44ad; color:#fff; }
  .btn-secondary { background:#aaa; color:#fff; }
  .btn:hover { opacity:.88; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#9997; Thêm Tác Giả Mới</h2>
  <form action="AuthorServlet" method="post">
    <input type="hidden" name="action" value="add"/>
    <div class="form-group">
      <label>Họ và Tên *</label>
      <input type="text" name="fullname" required placeholder="Tên tác giả..."/>
    </div>
    <div class="form-group">
      <label>Thông tin tác giả</label>
      <input type="text" name="bio" required placeholder="Thông tin của tác giả..."/>	
    </div>
    <div class="actions">
      <button type="submit" class="btn btn-primary">Lưu</button>
      <a href="AuthorServlet?action=list" class="btn btn-secondary">Hủy</a>
    </div>
  </form>
</div>
</body>
</html>
