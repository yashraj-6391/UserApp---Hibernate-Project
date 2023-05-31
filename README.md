# UserApp---Hibernate-Project
A CRUD project using hibernate ORM tool

1. Create from scratch , hibernate n maven based Java SE application.

Steps to create Hibernate projct.
1. Change perspective to Java

2. Create Maven Project ---skip archetype selection --
grp id,artifact id , packaging option : jar --finish

3. Creates default pom.xml . Add <build> n <dependencies> tags from text file - pom file.

4. Update the project .
R click on the project --> Maven --> Update Project -->select Force update checkbox -->Finish

4. Edit hibernate.cfg.xml , as per your DB settings.
5. Take help of hib-fgg-xml text file

6. Create HibernateUtils class to create singleton , immutable , inherently thrd safe SessionFactory instance.

7. Create a class TestHibernate under <tester> package.
Add following code. 

import static utils.HibernateUtils.*;
import org.hibernate.*;

public class TestHibernate {

	public static void main(String[] args) {
		try(SessionFactory sf=getSf())
		{
			System.out.println("Hibernate booted.....");
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}

8. Run this as "java application" , check console to see , 
sf created & hib booted .

Above confirms bootstrapping of hibernate framework.

9. Create a POJO n test auto table creation
9.1 Create a User POJO
Add these Data members
 userId (PK) ,name,email,password,confirmPassword,role(enum), regAmount;
	 LocalDate/Date regDate;
	 byte[] image;
Add JPA annotations (javax.persistence)
Confirm auto table creation.

9.2 Add <mapping> entry per POJO in hibernate.cfg.xml
Run TestHibernate to confirm auto table creation.

10. Create Hibernate based DAO layer , to insert a record.
10.1 DAO layer i/f
String registerUser(User user);
10.2 Hibenrate based DAO implementation class
no data mebers, no constr,no clean up
 CRUD method

11. Create a main(...) based tester to test entire application , for user registration.
