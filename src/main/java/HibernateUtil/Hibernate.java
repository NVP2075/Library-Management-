package HibernateUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Hibernate {
	private static EntityManagerFactory emf;
	public static EntityManagerFactory getEmf() {
		if(emf==null)
			emf = Persistence.createEntityManagerFactory("librarymanagement");
		return emf;
	}
}
