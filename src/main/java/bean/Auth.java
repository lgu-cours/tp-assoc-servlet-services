package bean;

public class Auth {
	private String login;
	private String password;
	
	public Auth(String login, String passowrd) {
		this.login = login;
		this.password = passowrd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
