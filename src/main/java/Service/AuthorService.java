package Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import Entity.Author;
import HibernateUtil.Hibernate;

public class AuthorService {
	public List<Author> getList(){
		EntityManager em = Hibernate.getEmf().createEntityManager();
		List<Author> authorList = new ArrayList<Author>();
		try {
			authorList = em.createQuery("from Author").getResultList(); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return authorList;
	}
	
	public void addAuthor(Author author) {
		EntityManager em = Hibernate.getEmf().createEntityManager();
		List<Author> authorList = getList();
		try {
			em.getTransaction().begin();
			em.persist(author);
			em.getTransaction().commit();
			/*if(authorList.contains(author) && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				em.close();
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
	}
	
	public void deleteAuthor(int id) {
		EntityManager em = Hibernate.getEmf().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(Author.class, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}
	}
	public Author findById(int id) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        Author a = null;
        try {
            a = em.find(Author.class, id);
        } finally {
            em.close();
        }
        return a;
    }
}
