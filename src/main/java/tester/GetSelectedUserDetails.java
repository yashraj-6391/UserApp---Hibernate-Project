package tester;

import java.util.Scanner;

import dao.UserDaoImpl;
import pojos.Role;

import static java.time.LocalDate.parse;

public class GetSelectedUserDetails {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter begin date , end date , role");
			dao.getSelectedUsers(parse(sc.next()), parse(sc.next()), Role.valueOf(sc.next().toUpperCase()))
					.forEach(System.out::println);
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
