<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav style="background:#1e3a5f;padding:12px 24px;display:flex;align-items:center;gap:32px;box-shadow:0 2px 6px rgba(0,0,0,0.3);">
  <span style="color:#fff;font-size:20px;font-weight:700;letter-spacing:1px;">&#128218; STU<sub>LBM</sub></span>
  <a href="${pageContext.request.contextPath}/index.jsp" style="color:#cce0ff;text-decoration:none;font-size:14px;">Dashboard</a>
  <a href="${pageContext.request.contextPath}/BookServlet?action=list" style="color:#cce0ff;text-decoration:none;font-size:14px;">Sách</a>
  <a href="${pageContext.request.contextPath}/AuthorServlet?action=list" style="color:#cce0ff;text-decoration:none;font-size:14px;">Tác Giả</a>
  <a href="${pageContext.request.contextPath}/MemberServlet?action=list" style="color:#cce0ff;text-decoration:none;font-size:14px;">Thành Viên</a>
  <a href="${pageContext.request.contextPath}/LendingServlet?action=list" style="color:#cce0ff;text-decoration:none;font-size:14px;">Mượn/Trả</a>
  <a href="${pageContext.request.contextPath}/LendingServlet?action=overdue" style="color:#ffb3b3;text-decoration:none;font-size:14px;">Quá Hạn</a>
</nav>
