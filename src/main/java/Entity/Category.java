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
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "cate", fetch = FetchType.LAZY)
	private List<Book> book_list;
	public void addBook(Book book) {
		book_list.add(book);
		book.setCate(this);
	}
	public void remove(Book book) {
		book_list.remove(book);
		book.setCate(null);
	}
	public Category() {
		super();
	}
	public Category(int id, String name, String description, List<Book> book_list) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.book_list = book_list;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Book> getBook_list() {
		return book_list;
	}
	public void setBook_list(List<Book> book_list) {
		this.book_list = book_list;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
