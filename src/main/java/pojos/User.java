package pojos;
/*
 * userId (PK) ,name,email,password,confirmPassword,role(enum), regAmount;
	 LocalDate/Date regDate;
	 byte[] image;
 */

import java.time.LocalDate;
import javax.persistence.*;

@Entity //MANDATRY class level anno meant for hibernate => following is entity class
@Table(name = "users_tbl")
public class User {
	@Id //mandatory field or property level(getter) annotation => PK 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //=> constraint : auto increment : OPTIONAL
	@Column(name = "user_id") //col name
	private Integer userId;
	@Column(length = 30)//=>varchar(30)
	private String name;
	@Column(length = 30,unique = true)//=>varchar(30) , unique constraint
	private String email;
	@Column(length = 20)
	private String password;
	@Transient //=> skip from persistence( no col.)
	private String confirmPassword;
	@Enumerated(EnumType.STRING) //=> col type : varchar storing enum constant name
	@Column(length = 20,name="user_role")
	private Role userRole;
	@Column(name="reg_amount")
	private double regAmount;
	@Column(name="reg_date")
	private LocalDate regDate;//col type : date
	@Lob
	private byte[] image;
	public User() {
		System.out.println("in def ctor of "+getClass());
	}
	
	public User(String name, String email, String password, String confirmPassword, Role userRole, double regAmount,
			LocalDate regDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.userRole = userRole;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}
	//parameterized ctor
	public User(String name, String email, double regAmount, LocalDate regDate) {
		super();
		this.name = name;
		this.email = email;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	//all setters n getters
	public Integer getUserId() {
		return userId;
	}
	

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	public double getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", userRole=" + userRole
				+ ", regAmount=" + regAmount + ", regDate=" + regDate;
	}
	
	
	
}
