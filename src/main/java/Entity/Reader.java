package Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "readers")
public class Reader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String account_status;
	@OneToMany(mappedBy = "reader", fetch = FetchType.LAZY)
	private List<BorrowTicket> borrow_ticket_list;
	private int totalBookBorrowed = 0;
	public void addBorrowTicket(BorrowTicket bt) {
		borrow_ticket_list.add(bt);
		bt.setReader(this);
	}
	public void removeBorrowTicket(BorrowTicket bt) {
		borrow_ticket_list.remove(bt);
		bt.setReader(null);
	}
	public Reader() {
		super();
	}
	public Reader(int id, String name, String email, String phone, String address, String account_status,
			List<BorrowTicket> borrow_ticket_list) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.account_status = account_status;
		this.borrow_ticket_list = borrow_ticket_list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	public List<BorrowTicket> getBorrow_ticket_list() {
		return borrow_ticket_list;
	}
	public void setBorrow_ticket_list(List<BorrowTicket> borrow_ticket_list) {
		this.borrow_ticket_list = borrow_ticket_list;
	}
	public int getTotalBookBorrowed() {
		return totalBookBorrowed;
	}
	public void setTotalBookBorrowed(int totalBookBorrowed) {
		totalBookBorrowed = 
		this.totalBookBorrowed = totalBookBorrowed;
	}
	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", account_status=" + account_status + ", borrow_ticket_list=" + borrow_ticket_list
				+ ", totalBookBorrowed=" + totalBookBorrowed + "]";
	}
	
	
}
