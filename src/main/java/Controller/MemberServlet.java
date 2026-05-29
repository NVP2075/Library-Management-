package Controller;

import Entity.Reader;
import Service.BorrowingBookService;
import Service.ReaderSerivce;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {

    private static final ReaderSerivce readerSer = new ReaderSerivce();
    private static final BorrowingBookService borrowBookSer = new BorrowingBookService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": {
                String kw = request.getParameter("keyword");
                List<Reader> members = (kw != null && !kw.trim().isEmpty())
                        ? readerSer.searchByName(kw.trim())
                        : readerSer.getAll();
                request.setAttribute("members", members);
                request.setAttribute("keyword", kw);
                forward(request, response, "/WEB-INF/view/member/list.jsp");
                break;
            }
            case "detail": {
                int id = Integer.parseInt(request.getParameter("id"));
                Reader member = readerSer.findById(id);
                request.setAttribute("member", member);
                request.setAttribute("lendings", borrowBookSer.getByMember(id));
                forward(request, response, "/WEB-INF/view/member/detail.jsp");
                break;
            }
            case "addForm":
                forward(request, response, "/WEB-INF/view/member/form.jsp");
                break;
            case "add": {
            	Reader m = new Reader();
                m.setName(request.getParameter("fullname"));
                m.setEmail(request.getParameter("email"));
                m.setPhone(request.getParameter("phone"));
                m.setAddress(request.getParameter("address"));
                m.setAccount_status("ACTIVE");
                readerSer.add(m);
                response.sendRedirect("MemberServlet?action=list");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                readerSer.delete(id);
                response.sendRedirect("MemberServlet?action=list");
                break;
            }
            default:
                response.sendRedirect("MemberServlet?action=list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String path)
            throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, res);
    }
}
