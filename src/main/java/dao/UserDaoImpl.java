package dao;

import pojos.Role;
import pojos.User;
import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.*;

public class UserDaoImpl implements IUserDao {

	@Override
	public String saveUser(User user) {

		Session session = getFactory().openSession();

		Transaction tx = session.beginTransaction();

		try {
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null)
				session.close();
		}

		return "User details inserted with user ID " + user.getUserId();
	}

	@Override
	public String saveUserWithGetCurntSession(User user) {
		Session session = getFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null)
				session.close();
		}

		return "User details inserted with user ID " + user.getUserId();

	}

	@Override
	public User getUserDetails(int userId) {

		User user;

		Session session = getFactory().getCurrentSession();
		Transaction tx = session.getTransaction();

		try {
			user = session.get(User.class, userId);
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return user;
	}

	@Override
	public List<User> getAllUserDetails() {

		List<User> userList=null;
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		
		try {
			userList = session.createQuery("select u from User u", User.class).getResultList();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return userList;
	}

	@Override
	public List<User> getSelectedUsers(LocalDate startDate, LocalDate endDate, Role role1) {
		
		String jpql = "select u from User u where u.regDate between :start and :end and u.userRole=:role";
		List<User> users = null;
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("start", startDate).setParameter("end", endDate)
					.setParameter("role", role1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return users;
		
	}

	@Override
	public List<String> getSelectedUserNames(LocalDate start) {
		
		String jpql = "select u.name from User u where u.regDate > :dt";
		List<String> names = null;
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			names = session.createQuery(jpql, String.class).setParameter("dt", start).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return names;
	}

	@Override
	public List<User> getSelectedUserDetails(LocalDate start) {
		String jpql = "select new pojos.User(name,email,regAmount,regDate) from User u where u.regDate > :dt";
		List<User> users = null;
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("dt", start).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return users;
	}

	@Override
	public String changePassword(String email, String oldPwd, String newPwd) {
		String mesg = "Password updating failed....";
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		User u = null;
		try {
			u = session.createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", oldPwd)
					.getSingleResult();
			
			u.setPassword(newPwd);
			tx.commit();
			
			mesg = "Password updated....";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		
		return mesg;
	}

	@Override
	public String applyBulkDiscount(LocalDate date, double discount) {
		String mesg = "Bulk updation failed!!!!!!!!!!!!";
		String jpql = "update User u set u.regAmount=u.regAmount-:disc where u.userRole=:role and u.regDate< :dt";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount = session.createQuery(jpql).setParameter("disc", discount)
					.setParameter("role", Role.CUSTOMER).setParameter("dt", date).executeUpdate();
			// L1 cache : EMPTY (i.e update query by passes the cache!)
			tx.commit();
			mesg = updateCount + " users got discount....";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteUserDetails(int userId) {
		String mesg = "Un subscription failed : invalid user id !!!!!!!!!!!!!!";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// get user from it's id
			User user = session.get(User.class, userId);// int --> Integer ---> up casting --> Serializable
			if (user != null) {
				// user : PERSISTENT
				session.delete(user); // entity is simply marked for removal! : user --REMOVED
				mesg = "User with name " + user.getName() + " un -subscribed....";
			}
			tx.commit();// session.flush --auto dirty chking --delete query --rec is deleted from DB ,
						// session.close=> L1 cache is destroyed , DB cn rets to the pool
			// user : TRANSIENT

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		
		return mesg;
	}

	@Override
	public String bulkDeleteUsers(LocalDate date, double amount) {
		String mesg = "Bulk Deletion failed !!!!!!!!!!!!";
		String jpql = "delete from User u where u.regDate > :dt and u.regAmount < :amt";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount = session.createQuery(jpql).
					setParameter("dt", date).setParameter("amt", amount)
					.executeUpdate();
			tx.commit();
			mesg=updateCount+" users deleted.....";

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
