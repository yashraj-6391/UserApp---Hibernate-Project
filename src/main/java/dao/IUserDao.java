package dao;

import java.time.LocalDate;
import java.util.List;

import pojos.Role;
import pojos.User;

public interface IUserDao {

	String saveUser(User user);
	String saveUserWithGetCurntSession(User user);
	User getUserDetails(int userId);
	List<User> getAllUserDetails();
	List<User> getSelectedUsers(LocalDate start,LocalDate end,Role role);
	List<String> getSelectedUserNames(LocalDate start);
	List<User> getSelectedUserDetails(LocalDate start);
	String changePassword(String email, String oldPwd,String newPwd);
	String applyBulkDiscount(LocalDate date,double discount);
	String deleteUserDetails(int userId);
	String bulkDeleteUsers(LocalDate date, double amount);
}
