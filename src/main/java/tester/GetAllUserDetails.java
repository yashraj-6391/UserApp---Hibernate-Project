package tester;

import static utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class GetAllUserDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory()) {
			UserDaoImpl dao = new UserDaoImpl();
			dao.getAllUserDetails().forEach(System.out::println);
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
