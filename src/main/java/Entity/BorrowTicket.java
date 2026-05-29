package Entity;

import java.util.Date;
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
@Table(name = "borrow_tickets")
public class BorrowTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "reader_id")
	private Reader reader;
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;
	private Date borrow_date;
	private String status;
	@OneToMany(mappedBy = "ticket_id",fetch = FetchType.EAGER)
	private List<BorrowTicketDetail> borrow_ticket_detail_list;
	public BorrowTicket() {
		super();
	}
	public void addBTDetail(BorrowTicketDetail btd) {
		borrow_ticket_detail_list.add(btd);
		btd.setTicket_id(this);
	}
	public void removeBTDetail(BorrowTicketDetail btd) {
		borrow_ticket_detail_list.remove(btd);
		btd.setTicket_id(null);
	}
	public BorrowTicket(int id, Reader reader, Staff staff, Date borrow_date, String status,
			List<BorrowTicketDetail> borrow_ticket_detail_list) {
		super();
		this.id = id;
		this.reader = reader;
		this.staff = staff;
		this.borrow_date = borrow_date;
		this.status = status;
		this.borrow_ticket_detail_list = borrow_ticket_detail_list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
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
	public boolean overDue() {
		Date today = new Date();
		if(today.getTime() - this.borrow_date.getTime() > 10L * 24 * 60 * 60 * 1000)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "borrow_tickets [id=" + id + ", reader=" + reader + ", staff=" + staff + ", borrow_date=" + borrow_date
				+ ", status=" + status + ", borrow_ticket_detail_list=" + borrow_ticket_detail_list + "]";
	}
	
	
	
}
