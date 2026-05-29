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
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String bio;
	@OneToMany(mappedBy = "auth",fetch = FetchType.LAZY)
	private List<Book> book_list;
	public Author() {
		super();
	}
	public Author(int id, String name, String bio, List<Book> book_list) {
		super();
		this.id = id;
		this.name = name;
		this.bio = bio;
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
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public List<Book> getBook_list() {
		return book_list;
	}
	public void setBook_list(List<Book> book_list) {
		this.book_list = book_list;
	}
	public void addBook(Book book) {
		book_list.add(book);
		book.setAuth(this);
	}
	public void remove(Book book) {
		book_list.remove(book);
		book.setAuth(null);
	}
	
	@Override
	public String toString() {
		return "authors [id=" + id + ", name=" + name + ", bio=" + bio + ", book_list=" + book_list + "]";
	}
	
}
