package Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book_copies")
public class BookCopy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	private String barcode;
	private String status;
	@OneToMany(mappedBy = "book_copy", fetch = FetchType.LAZY)
	private List<BorrowTicketDetail> borrow_ticket_detail_list;
	public BookCopy() {
		super();
	}
	public void addBTDetail(BorrowTicketDetail btd) {
		borrow_ticket_detail_list.add(btd);
		btd.setBook_copy(this);
	}
	public void removeBTDetail(BorrowTicketDetail btd) {
		borrow_ticket_detail_list.remove(btd);
		btd.setBook_copy(null);
	}
	public BookCopy(int id, Book book, String barcode, String status,
			List<BorrowTicketDetail> borrow_ticket_detail_list) {
		super();
		this.id = id;
		this.book = book;
		this.barcode = barcode;
		this.status = status;
		this.borrow_ticket_detail_list = borrow_ticket_detail_list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BorrowTicketDetail> getBorrow_ticket_detail_list() {
		return borrow_ticket_detail_list;
	}
	public void setBorrow_ticket_detail_list(List<BorrowTicketDetail> borrow_ticket_detail_list) {
		this.borrow_ticket_detail_list = borrow_ticket_detail_list;
	}
	@Override
	public String toString() {
		return "book_copies [id=" + id + ", book=" + book + ", barcode=" + barcode + ", status=" + status
				+ ", borrow_ticket_detail_list=" + borrow_ticket_detail_list + "]";
	}
	
	
}
