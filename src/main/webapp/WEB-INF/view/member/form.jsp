<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thêm Thành Viên</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; }
  .container { max-width:500px; margin:32px auto; background:#fff; padding:32px; border-radius:12px; box-shadow:0 2px 12px rgba(0,0,0,.09); }
  h2 { margin-bottom:24px; color:#1e3a5f; }
  .form-group { margin-bottom:16px; }
  label { display:block; font-size:13px; font-weight:600; margin-bottom:5px; color:#555; }
  input[type=text], input[type=email], textarea {
    width:100%; padding:9px 12px; border:1px solid #ccc; border-radius:6px; font-size:14px;
  }
  input:focus, textarea:focus { outline:none; border-color:#2e6da4; box-shadow:0 0 0 2px rgba(46,109,164,.15); }
  textarea { resize:vertical; height:70px; }
  .actions { display:flex; gap:12px; margin-top:24px; }
  .btn { padding:10px 22px; border:none; border-radius:6px; cursor:pointer; font-size:14px; text-decoration:none; }
  .btn-primary { background:#2e6da4; color:#fff; }
  .btn-secondary { background:#aaa; color:#fff; }
  .btn:hover { opacity:.88; }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="container">
  <h2>&#128100; Thêm Thành Viên Mới</h2>
  <form action="MemberServlet" method="post">
    <input type="hidden" name="action" value="add"/>
    <div class="form-group">
      <label>Họ và Tên *</label>
      <input type="text" name="fullname" required placeholder="Nguyễn Văn A"/>
    </div>
    <div class="form-group">
      <label>Email</label>
      <input type="email" name="email" placeholder="email@example.com"/>
    </div>
    <div class="form-group">
      <label>Số Điện Thoại</label>
      <input type="text" name="phone" placeholder="09xxxxxxxx"/>
    </div>
    <div class="form-group">
      <label>Địa Chỉ</label>
      <textarea name="address" placeholder="Địa chỉ liên hệ..."></textarea>
    </div>
    <div class="actions">
      <button type="submit" class="btn btn-primary">Lưu</button>
      <a href="MemberServlet?action=list" class="btn btn-secondary">Hủy</a>
    </div>
  </form>
</div>
</body>
</html>
