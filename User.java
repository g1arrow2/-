package arrow1.Domain;

public class User implements java.io.Serializable {  //序列化
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String username;
 private String password;
 private String sex;
 private String email;
 
public User(String username,String password,String sex,String email) {
	
this.password=password;
this.username=username;
this.sex=sex;
this.email=email;
 }
public User() {
	
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
 public String toString() {
	 return "用户名=" + username+",密码="+password+",性别="+sex+",email="+email;
 }
}
