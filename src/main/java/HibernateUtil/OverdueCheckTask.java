package HibernateUtil;

import java.util.Date;

import javax.persistence.EntityManager;

public class OverdueCheckTask implements Runnable {
	@Override
    public void run() {
        EntityManager em = Hibernate.getEmf().createEntityManager(); 
        try {
        	em.getTransaction().begin();

        	Date now = new Date();

        	
        	String updateDetailHql = "UPDATE BorrowTicketDetail d SET d.status = 'OVERDUE' " +
        	                         "WHERE d.status = 'BORROWED' AND d.due_date < :now AND d.return_date IS NULL";
        	int detailCount = em.createQuery(updateDetailHql)
        	                    .setParameter("now", now)
        	                    .executeUpdate();

        	
        	String updateTicketHql = "UPDATE BorrowTicket t SET t.status = 'OVERDUE' " +
        	                         "WHERE t.status = 'ACTIVE' AND t.id IN " +
        	                         "(SELECT d.ticket_id.id FROM BorrowTicketDetail d WHERE d.status = 'OVERDUE')";
        	int ticketCount = em.createQuery(updateTicketHql)
        	                    .executeUpdate();

        	em.getTransaction().commit();

        	if (detailCount > 0 || ticketCount > 0) {
        	    System.out.println("[Scheduler] Đã tự động cập nhật " + detailCount + " cuốn sách và " + ticketCount + " phiếu mượn sang Quá Hạn.");
        	}
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
