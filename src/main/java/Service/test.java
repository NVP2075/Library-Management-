package Service;

import java.util.List;

import HibernateUtil.Hibernate;

public class test {
	
	public static void main(String[] args) {
		BorrowingBookService t = new BorrowingBookService();
		System.out.println(t.getActive());
	}

}
