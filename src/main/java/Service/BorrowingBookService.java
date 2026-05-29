package Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Book;
import Entity.BookCopy;
import Entity.BorrowTicket;
import Entity.BorrowTicketDetail;
import Entity.Reader;
import Entity.Staff;
import HibernateUtil.Hibernate;

public class BorrowingBookService {
	private static final int MAX_BOOKS = 5;
    private static final int LOAN_DAYS = 10;
    private static final double FINE_PER_DAY = 1000.0; // VND per day

    
    
    public List<Staff> getStaffList() {
    	EntityManager em = Hibernate.getEmf().createEntityManager();
    	List<Staff> staffs = new ArrayList<Staff>();
    	try {
			staffs = em.createQuery("from Staff").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return staffs;
    }
    
    public List<BorrowTicket> getAll() {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        List<BorrowTicket> list = new ArrayList<>();
        try {
            list = em.createQuery(
                "from BorrowTicket bt order by bt.borrow_date desc",
                BorrowTicket.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public List<BorrowTicket> getActive() {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        List<BorrowTicket> list = new ArrayList<>();
        try {
            list = em.createQuery(
                "from BorrowTicket bt where bt.status = 'ACTIVE' order by bt.borrow_date asc",
                BorrowTicket.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public List<BorrowTicket> getByMember(int readerId) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        List<BorrowTicket> list = new ArrayList<>();
        try {
            TypedQuery<BorrowTicket> q = em.createQuery(
                "select distinct bt from BorrowTicket bt"
                +" left join fetch bt.borrow_ticket_detail_list" 
                +" where bt.reader.id = :mid"
                +" order by bt.borrow_date desc",
                BorrowTicket.class);
            q.setParameter("mid", readerId);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public List<BorrowTicket> getOverdue() {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        List<BorrowTicket> list = new ArrayList<>();
        try {
            list = em.createQuery(
                "from BorrowTicket bt where bt.status = 'OVERDUE'",
                BorrowTicket.class)
                .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public BookCopy bookCopyById(int id) {
    	EntityManager em = Hibernate.getEmf().createEntityManager();
    	BookCopy bc= new BookCopy();
    	try {
			TypedQuery<BookCopy> q = em.createQuery("from BookCopy bc where bc.id =:id",BookCopy.class)
					.setParameter("id", id);
			bc = q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return bc;
    }
    
    /**
     * Check out a book item to a member.
     * Returns error message string, or null on success.
     */
    public String checkOut(int bookItemId, int readerId, int staffId) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();

            Reader reader = em.find(Reader.class, readerId);
            if (reader == null) return "Không tìm thấy người đọc";
            if (reader.getTotalBookBorrowed() >= MAX_BOOKS)
                return "Thành viên đã đạt giới hạn " + MAX_BOOKS + " cuốn sách.";
            if (!"ACTIVE".equals(reader.getAccount_status()))
                return "Tài khoản thành viên không hoạt động.";
            
            
            Staff staff = em.find(Staff.class, staffId);
            if(staff == null) return "Thủ thư không tồn tại";
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, LOAN_DAYS);
            Date dueDate = cal.getTime();

            
            Date today = new Date();
            BorrowTicket borrow;
            BookCopy bc = bookCopyById(bookItemId);
            if(bc!=null && "AVAILABLE".equals(bc.getStatus())) {
               
                borrow = new BorrowTicket(0,reader,staff,today ,"ACTIVE",null);
                em.persist(borrow);
                BorrowTicketDetail btd = new BorrowTicketDetail(borrow,bc,dueDate,null,0,"BORROWED");	   
                em.merge(btd);
                bc.setStatus("BORROWED");
                em.merge(bc);
                reader.setTotalBookBorrowed(reader.getTotalBookBorrowed() + 1);
            } else return "Không tìm thấy bản sao sách.";
       
            em.getTransaction().commit();
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return "Lỗi hệ thống: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    /**
     * Return a book. Calculates fine if overdue.
     * Returns fine amount (0 if on time).
     */
    
    public BorrowTicketDetail getBorrowTicketDetailById(int borrowTicketID){
    	EntityManager em = Hibernate.getEmf().createEntityManager();
    	BorrowTicketDetail btd = new BorrowTicketDetail();
    	try {
			TypedQuery<BorrowTicketDetail> query = em.createQuery("from BorrowTicketDetail btd where btd.id =:borrowTicketID",BorrowTicketDetail.class);
			query.setParameter("borrowTicketID", borrowTicketID);
			btd = query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return btd;
    }
    
    public double returnBook(int borrowId) {
    	EntityManager em = Hibernate.getEmf().createEntityManager();
        double fineAmount = 0;
        try {
            em.getTransaction().begin();

            BorrowTicket borrow = em.find(BorrowTicket.class, borrowId);
            if (borrow == null || !"ACTIVE".equals(borrow.getStatus()) && !"OVERDUE".equals(borrow.getStatus())) {
                return -1;	
            }

            Date today = new Date();
                        
            BorrowTicketDetail btd = em.createQuery("SELECT d FROM BorrowTicketDetail d WHERE d.ticket_id.id = :bId", BorrowTicketDetail.class)
                    .setParameter("bId", borrowId)
                    .setMaxResults(1) 
                    .getSingleResult();
            
            if (today.after(btd.getDue_date())) {
                long diff = today.getTime() - btd.getDue_date().getTime();
                long overDueDays = diff / (1000 * 60 * 60 * 24);
                fineAmount = overDueDays * FINE_PER_DAY;
                btd.setFine_amount(fineAmount);
            }
             
            btd.setStatus("RETURNED");
            btd.setReturn_date(today);
            
            BookCopy bc = btd.getBook_copy();
            bc.setStatus("AVAILABLE"); 
            
            borrow.setStatus("RETURNED"); 
                     
            Reader reader = borrow.getReader();
            int current = reader.getTotalBookBorrowed();
            reader.setTotalBookBorrowed(Math.max(0, current - 1));

            

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return -1;
        } finally {
            em.close();
        }
        return fineAmount;
    }

    public BorrowTicket findById(int id) {
        EntityManager em = Hibernate.getEmf().createEntityManager();
        BorrowTicket bt = null;
        try {
            bt = em.find(BorrowTicket.class, id);
        } finally {
            em.close();
        }
        return bt;
    }
}
