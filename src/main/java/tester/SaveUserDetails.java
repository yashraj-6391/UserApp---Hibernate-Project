package tester;


import java.time.LocalDate;
import java.util.Scanner;


import dao.UserDaoImpl;
import pojos.Role;
import pojos.User;

public class SaveUserDetails {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create user dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println(
					"Enter user details  name,  email,  password,  confirmPassword,  userRole,  regAmount	 regDate(yr-mon-day)");
			User user = new User(sc.next(), sc.next(), sc.next(), sc.next(), Role.valueOf(sc.next().toUpperCase()),
					sc.nextDouble(), LocalDate.parse(sc.next()));
			System.out.println(dao.saveUser(user));
		} // sf.close() => immediate closing of cn pool
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
