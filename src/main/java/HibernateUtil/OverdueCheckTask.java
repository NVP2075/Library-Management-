package HibernateUtil;

import java.util.Date;

import javax.persistence.EntityManager;

public class OverdueCheckTask implements Runnable {
	@Override
    public void run() {
        EntityManager em = Hibernate.getEmf().createEntityManager(); 
        try {
            em.getTransaction().begin();
      
            String hql = "UPDATE BorrowTicketDetail b SET b.status = 'OVERDUE' " +
                         "WHERE b.status = 'ACTIVE' AND b.due_date < :now AND b.return_date IS NULL";
            
            int updatedCount = em.createQuery(hql)
                                 .setParameter("now", new Date())
                                 .executeUpdate();
            
            em.getTransaction().commit();
            
            if (updatedCount > 0) {
                System.out.println("[Scheduler] Đã tự động cập nhật " + updatedCount + " phiếu mượn sang trạng thái Quá Hạn.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
