package tester;

import java.util.Scanner;
import dao.UserDaoImpl;

public class ChangePassword {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter email , old password , new password");
			System.out.println("status " + dao.changePassword(sc.next(), sc.next(), sc.next()));
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
