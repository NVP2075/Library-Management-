package Service;

import Entity.Author;
import Entity.Book;
import Entity.BookCopy;
import Entity.BorrowTicketDetail;
import Entity.Category;
import HibernateUtil.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BookService {
	private static final BorrowingBookService borrowingBookSer = new BorrowingBookService();

    public List<Book> getAll() {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        List<Book> list = new ArrayList<>();
        try {
            list = em.createQuery("from Book", Book.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public Book findById(int id) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        Book book = null;
        try {
            book = em.find(Book.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return book;
    }
    
    public Category findByCateId(int id) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        Category cate = null;
        try {
            cate = em.find(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return cate;
    } 
    public List<Category> listCate() {
    	EntityManager em = Hibernate.getEmf().createEntityManager();
    	List<Category> list_cate = new ArrayList<Category>();
    	try {
			list_cate = em.createQuery("from Category").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return list_cate;
    }

    public List<Book> searchByTitle(String keyword) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        List<Book> list = new ArrayList<>();
        try {
            TypedQuery<Book> q = em.createQuery(
                "from Book b where lower(b.title) like :kw", Book.class);
            q.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public List<Book> searchBySubject(String subject) {
        EntityManager em =Hibernate.getEmf().createEntityManager();
        List<Book> list = new ArrayList<>();
        try {
            TypedQuery<Book> q = em.createQuery(
                "from Book b where lower(b.cate.name) like :kw", Book.class);
            q.setParameter("kw", "%" + subject.toLowerCase() + "%");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public void add(Book book) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Book book) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            Book book = em.find(Book.class, id);
            if (book != null) em.remove(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void addBookCopy(int bookId, String barcode, String status) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            Book book = em.find(Book.class, bookId);
         
            if (book != null) {
                BookCopy bc = new BookCopy(0,book,barcode,status,null);
                em.persist(bc);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public long countAvailableItems(int bookId) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        long count = 0;
        try {
            count = em.createQuery(
                "select count(bc) from BookCopy bc where bc.book.id = :bid and bi.status = 'Available'",Long.class)
                .setParameter("bid", bookId)
                .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return count;
    }
    
    public void addCate(Category cate) {
    	EntityManager em = Hibernate.getEmf().createEntityManager();
    	try {
    		em.getTransaction().begin();
    		em.persist(cate);
    		em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			e.printStackTrace();
		}
    	finally {
			em.close();
		}
    }
}
