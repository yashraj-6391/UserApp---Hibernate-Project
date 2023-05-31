package tester;

import java.util.Scanner;
import dao.UserDaoImpl;

public class GetUserDetails {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter user id");
			System.out.println(dao.getUserDetails(sc.nextInt()));
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
