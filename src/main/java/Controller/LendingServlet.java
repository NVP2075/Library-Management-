package Controller;

import Entity.Book;
import Entity.BookCopy;
import Entity.BorrowTicket;
import Entity.BorrowTicketDetail;
import Entity.Reader;
import Entity.Staff;
import Service.BookService;
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

@WebServlet("/LendingServlet")
public class LendingServlet extends HttpServlet {

    private static final BorrowingBookService borrowBookSer = new BorrowingBookService();
    private static final BookService bookSer = new BookService();
    private static final ReaderSerivce readerSer = new ReaderSerivce();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": {
                List<BorrowTicket> borrows = borrowBookSer.getActive();
                request.setAttribute("lendings", borrows);
                forward(request, response, "/WEB-INF/view/lending/list.jsp");
                break;
            }
            case "overdue": {
                List<BorrowTicket> overdue = borrowBookSer.getOverdue();
                request.setAttribute("lendings", overdue);
                request.setAttribute("pageTitle", "Sách Quá Hạn");
                forward(request, response, "/WEB-INF/view/lending/list.jsp");
                break;
            }
            case "checkoutForm": {
                List<Book> books = bookSer.getAll();
                List<Reader> members = readerSer.getAll();
                List<Staff> staffs = borrowBookSer.getStaffList();
                request.setAttribute("books", books);
                request.setAttribute("members", members);
                request.setAttribute("staffs", staffs);
                forward(request, response, "/WEB-INF/view/lending/checkout.jsp");
                break;
            }
            case "checkout": {
                int bookItemId = Integer.parseInt(request.getParameter("bookItemId"));
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                String error = borrowBookSer.checkOut(bookItemId, memberId,staffId);
                if (error != null) {
                    request.setAttribute("error", error);
                    List<Book> books = bookSer.getAll();
                    List<Reader> readers = readerSer.getAll();
                    request.setAttribute("books", books);
                    request.setAttribute("members", readers);
                    forward(request, response, "/WEB-INF/view/lending/checkout.jsp");
                } else {
                    response.sendRedirect("LendingServlet?action=list");
                }
                break;
            }
            case "return": {
                int lendingId = Integer.parseInt(request.getParameter("id"));
                double fine = borrowBookSer.returnBook(lendingId);
                request.setAttribute("fine", fine);
                request.setAttribute("lendingId", lendingId);
                forward(request, response, "/WEB-INF/view/lending/returnResult.jsp");
                break;
            }
            case "history": {
                List<BorrowTicket> all = borrowBookSer.getAll();
                request.setAttribute("lendings", all);
                request.setAttribute("pageTitle", "Lịch Sử Mượn Sách");
                forward(request, response, "/WEB-INF/view/lending/list.jsp");
                break;
            }
            default:
                response.sendRedirect("LendingServlet?action=list");
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
