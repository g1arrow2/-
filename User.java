package practice6;

public class User implements java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String userName;
private String passWord;
private String email;
public User() {
}
public User(String userName, String passWord, String email) {
super();
this.setUserName(userName);
this.setPassWord(passWord);
this.setEmail(email);
}
public String getEmail() {
return email;
}
public void setEmail(String email) {
this.email = email;
}
public String getPassWord() {
return passWord;
}
public void setPassWord(String passWord) {
this.passWord = passWord;
}
public String getUserName() {
return userName;
}
public void setUserName(String userName) {
this.userName = userName;
}

public String toString(){
return userName+":"+passWord+":"+email;
}
}
