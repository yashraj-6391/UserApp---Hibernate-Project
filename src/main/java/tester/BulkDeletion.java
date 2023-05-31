package tester;

import java.util.Scanner;
import dao.UserDaoImpl;
import static java.time.LocalDate.parse;

public class BulkDeletion {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter date n reg amount");
			System.out.println("status " + dao.bulkDeleteUsers(parse(sc.next()), sc.nextDouble()));
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
