package tester;

import static java.time.LocalDate.parse;
import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class GetSelectedUserDetails2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter begin date");
			dao.getSelectedUserDetails(parse(sc.next())).forEach(System.out::println);
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
