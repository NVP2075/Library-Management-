package Entity;

import java.io.Serializable;
import java.util.Objects;

// Class này đại diện cho Khóa chính kép
public class BorrowTicketDetailPK implements Serializable {
    private int ticket_id;    // Tên biến phải khớp với tên biến trong class BorrowTicketDetail
    private int book_copy;  // Tên biến phải khớp với tên biến trong class BorrowTicketDetail

    public BorrowTicketDetailPK() {}

    // Bắt buộc phải có equals() và hashCode() cho Khóa kép
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowTicketDetailPK that = (BorrowTicketDetailPK) o;
        return ticket_id == that.ticket_id && book_copy == that.book_copy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket_id, book_copy);
    }
}