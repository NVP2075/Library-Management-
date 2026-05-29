package Controller;

import Entity.Author;
import Service.AuthorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AuthorServlet")
public class AuthorServlet extends HttpServlet {

    private static final AuthorService authorSer = new AuthorService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": {
                List<Author> authors = authorSer.getList();
                request.setAttribute("authors", authors);
                forward(request, response, "/WEB-INF/view/author/list.jsp");
                break;
            }
            case "addForm":
                forward(request, response, "/WEB-INF/view/author/form.jsp");
                break;
            case "add": {
                Author a = new Author();
                a.setName(request.getParameter("fullname"));
                a.setBio(request.getParameter("bio"));
                authorSer.addAuthor(a);
                response.sendRedirect("AuthorServlet?action=list");
                break;
            }
            default:
                response.sendRedirect("AuthorServlet?action=list");
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
