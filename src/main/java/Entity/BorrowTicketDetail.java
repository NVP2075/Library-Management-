package Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name ="borrow_ticket_details")
@IdClass(BorrowTicketDetailPK.class)
public class BorrowTicketDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private BorrowTicket ticket_id;
	@Id
	@ManyToOne
	@JoinColumn(name = "book_copy_id")
	private BookCopy book_copy;
	private Date due_date;
	private Date return_date;
	private double fine_amount;
	private String status;
	public BorrowTicketDetail() {
		super();
	}
	public BorrowTicketDetail(BorrowTicket ticket_id, BookCopy book_copy, Date due_date, Date return_date,
			double fine_amount, String status) {
		super();
		this.ticket_id = ticket_id;
		this.book_copy = book_copy;
		this.due_date = due_date;
		this.return_date = return_date;
		this.fine_amount = fine_amount;
		this.status = status;
	}
	public BorrowTicket getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(BorrowTicket ticket_id) {
		this.ticket_id = ticket_id;
	}
	public BookCopy getBook_copy() {
		return book_copy;
	}
	public void setBook_copy(BookCopy book_copy) {
		this.book_copy = book_copy;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public double getFine_amount() {
		return fine_amount;
	}
	public void setFine_amount(double fine_amount) {
		this.fine_amount = fine_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "borrow_ticket_details [ticket_id=" + ticket_id + ", book_copy=" + book_copy + ", due_date=" + due_date
				+ ", return_date=" + return_date + ", fine_amount=" + fine_amount + ", status=" + status + "]";
	}
	
}
