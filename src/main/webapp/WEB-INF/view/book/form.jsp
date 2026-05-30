<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thêm Sách Mới</title>
<style>
  * { box-sizing:border-box; margin:0; padding:0; }
  body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; color:#222; }
  .container { max-width:560px; margin:32px auto; background:#fff; padding:32px; border-radius:12px; box-shadow:0 2px 12px rgba(0,0,0,.09); }
  h2 { margin-bottom:24px; color:#1e3a5f; }
  .form-group { margin-bottom:16px; }
  label { display:block; font-size:13px; font-weight:600; margin-bottom:5px; color:#555; }
  input[type=text], select { width:100%; padding:9px 12px; border:1px solid #ccc; border-radius:6px; font-size:14px; }
  input:focus, select:focus { outline:none; border-color:#2e6da4; box-shadow:0 0 0 2px rgba(46,109,164,.15); }
  .checkbox-group { display:flex; flex-wrap:wrap; gap:10px; }
  .checkbox-group label { font-weight:400; display:flex; align-items:center; gap:5px; }
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
  <h2>&#10133; Thêm Sách Mới</h2>
  <form action="BookServlet" method="post">
    <input type="hidden" name="action" value="add"/>
    <div class="form-group">
      <label>Tiêu Đề</label>
      <input type="text" name="title" required placeholder="Tên sách..."/>
    </div>
    <div class="form-group">
      <label>Mô tả</label>
      <input type="text" name="subject" placeholder="Thêm mô tả của sách,..."/>
    </div>
    <div class="form-group">
      <label>Đường dẫn hình ảnh</label>
      <input type="text" name="publisher" placeholder="Link,..."/>
    </div>
    <div class="form-group">
      <label>Thể Loại</label>
      	<div class="checkbox-group">
      	<select name="categoryId">
      		<option>--Chọn thể loại--</option>
      		<c:forEach items="${listCate}" var="lc">
          		<option value="${lc.id }">${lc.name }</option>
        	</c:forEach>
      	</select>
      	<small style="color:#888;margin-top:4px;display:block;">
        Chưa có thể loại? <a href="AuthorServlet?action=addForm">Thêm thể loại mới</a>
      </small>
    	</div>
    </div>
    <div class="form-group">
      <label>Tác Giả</label>
      <div class="checkbox-group">
      	<select name="authorIds">
      		<option>--Chọn tác giả--</option>
      		<c:forEach items="${authors}" var="a">
          		<option value="${a.id }">${a.name }</option>
        	</c:forEach>
      	</select>
      </div>
      <small style="color:#888;margin-top:4px;display:block;">
        Chưa có tác giả? <a href="AuthorServlet?action=addForm">Thêm tác giả mới</a>
      </small>
    </div>
    <div class="actions">
      <button type="submit" class="btn btn-primary">Lưu</button>
      <a href="BookServlet?action=list" class="btn btn-secondary">Hủy</a>
    </div>
  </form>
</div>
</body>
</html>
