package tester;


import java.util.Scanner;
import dao.UserDaoImpl;
import static java.time.LocalDate.parse;

public class ApplyBulkDiscount {

	public static void main(String[] args) {
		try ( Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter date n discount");
			System.out.println("status " + dao.applyBulkDiscount(parse(sc.next()), sc.nextDouble()));
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
