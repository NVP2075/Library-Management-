package Controller;

import Entity.Author;
import Entity.Book;
import Entity.Category;
import Service.AuthorService;
import Service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

    private static final BookService bookSer = new BookService();
    private static final AuthorService authorSer = new AuthorService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": {
                String kw = request.getParameter("keyword");
                String subject = request.getParameter("subject");
                List<Book> books;
                if (kw != null && !kw.trim().isEmpty()) {
                    books = bookSer.searchByTitle(kw.trim());
                    request.setAttribute("keyword", kw);
                } else if (subject != null && !subject.trim().isEmpty()) {
                    books = bookSer.searchBySubject(subject.trim());
                    request.setAttribute("subject", subject);
                } else {
                    books = bookSer.getAll();
                }
                request.setAttribute("books", books);
                forward(request, response, "/WEB-INF/view/book/list.jsp");
                break;
            }
            case "detail": {
                int id = Integer.parseInt(request.getParameter("id"));
                Book book = bookSer.findById(id);
                request.setAttribute("book", book);
                forward(request, response, "/WEB-INF/view/book/detail.jsp");
                break;
            }
            case "addForm": {
                List<Author> authors = authorSer.getList();
                List<Category> cates = bookSer.listCate();
                request.setAttribute("authors", authors);
                request.setAttribute("listCate", cates);
                forward(request, response, "/WEB-INF/view/book/form.jsp");
                break;
            }
            case "add": {
            	
                String title = request.getParameter("title");
                String description = request.getParameter("subject");
                String imageUrl = request.getParameter("publisher");
                String categoryID = request.getParameter("categoryId");
                String authorIds = request.getParameter("authorIds");
                
                Author author = authorSer.findById(Integer.parseInt(authorIds));
                Category cate = bookSer.findByCateId(Integer.parseInt(categoryID));
                Book book = new Book(0, title, description, imageUrl,cate,author,null);
                bookSer.add(book);
                response.sendRedirect("BookServlet?action=list");
                break;
            }
            case "addItem": {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                String barcode = request.getParameter("barcode");
                String status = request.getParameter("status");
                bookSer.addBookCopy(bookId, barcode,status);
                response.sendRedirect("BookServlet?action=detail&id=" + bookId);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                bookSer.delete(id);
                response.sendRedirect("BookServlet?action=list");
                break;
            }
            case "addCateForm": {
            	forward(request, response, "./WEB-INF/view/book/addCate.jsp");
            	break;
            	
            }
            case "addCate":  {
            	String name = request.getParameter("name");
            	String des = request.getParameter("des");
            	Category cate = new Category(0,name,des,null);
            	bookSer.addCate(cate);
            	response.sendRedirect("BookServlet?action=list");
            	break;
            }
            default:
                response.sendRedirect("BookServlet?action=list");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String path)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req, res);
    }
}
