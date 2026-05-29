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
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private String image_url;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category cate;
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author auth;
	@OneToMany(mappedBy = "book",fetch = FetchType.EAGER)
	private List<BookCopy> book_copy_list;
	public Book() {
		super();
	}
	public void addBookCopies(BookCopy bc) {
		book_copy_list.add(bc);
		bc.setBook(this);
	}
	public void removeBookCopies(BookCopy bc) {
		book_copy_list.remove(bc);
		bc.setBook(null);
	}
	public Book(int id, String title, String description, String image_url, Category cate, Author auth,
			List<BookCopy> book_copy_list) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.image_url = image_url;
		this.cate = cate;
		this.auth = auth;
		this.book_copy_list = book_copy_list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Category getCate() {
		return cate;
	}
	public void setCate(Category cate) {
		this.cate = cate;
	}
	public Author getAuth() {
		return auth;
	}
	public void setAuth(Author auth) {
		this.auth = auth;
	}
	public List<BookCopy> getBook_copy_list() {
		return book_copy_list;
	}
	public void setBook_copy_list(List<BookCopy> book_copy_list) {
		this.book_copy_list = book_copy_list;
	}
	public long getAvailableCount() {
        return book_copy_list.stream()
                .filter(bc -> "AVAILABLE".equals(bc.getStatus()))
                .count();
    }
	@Override
	public String toString() {
		return "books [id=" + id + ", title=" + title + ", description=" + description + ", image_url=" + image_url
				+ ", cate=" + cate + ", auth=" + auth + ", book_copy_list=" + book_copy_list + "]";
	}
	
	
}
