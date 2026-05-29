package Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Reader;
import HibernateUtil.Hibernate;

public class ReaderSerivce {
	 public List<Reader> getAll() {
	        EntityManager em = Hibernate.getEmf().createEntityManager();
	        List<Reader> list = new ArrayList<Reader>();
	        try {
	            list = em.createQuery("from Reader", Reader.class).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	        return list;
	    }

	    public Reader findById(int id) {
	        EntityManager em = Hibernate.getEmf().createEntityManager();
	        Reader  m = null;
	        try {
	            m = em.find(Reader.class, id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	        return m;
	    }

	    public List<Reader> searchByName(String keyword) {
	        EntityManager em = Hibernate.getEmf().createEntityManager();
	        List<Reader> list = new ArrayList<>();
	        try {
	            TypedQuery<Reader> q = em.createQuery(
	                "from Reader r where lower(r.name) like :kw", Reader.class);
	            q.setParameter("kw", "%" + keyword.toLowerCase() + "%");
	            list = q.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	        return list;
	    }

	    public void add(Reader reader) {
	        EntityManager em = Hibernate.getEmf().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(reader);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

	    public void update(Reader reader) {
	        EntityManager em = Hibernate.getEmf().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.merge(reader);
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
	            Reader r = em.find(Reader.class, id);
	            if (r != null) em.remove(r);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

}
