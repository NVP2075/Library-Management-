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
@Table(name = "staffs")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String role;
	@OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
	private List<BorrowTicket> borrow_ticket_list;
	public void addBorrowTicket(BorrowTicket bt) {
		borrow_ticket_list.add(bt);
		bt.setStaff(this);
	}
	public void removeBorrowTicket(BorrowTicket bt) {
		borrow_ticket_list.remove(bt);
		bt.setStaff(null);
	}
	public Staff() {
		super();
	}
	public Staff(int id, String name, String role, List<BorrowTicket> borrow_ticket_list) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<BorrowTicket> getBorrow_ticket_list() {
		return borrow_ticket_list;
	}
	public void setBorrow_ticket_list(List<BorrowTicket> borrow_ticket_list) {
		this.borrow_ticket_list = borrow_ticket_list;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", role=" + role + ", borrow_ticket_list=" + borrow_ticket_list
				+ "]";
	}
}
