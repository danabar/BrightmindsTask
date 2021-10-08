package brightminds.task.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import brightminds.task.session.util.SessionUtils;

@ManagedBean
@SessionScoped
/**
 * this class is a managedbean controller which have all funcionality used in
 * the login page
 *
 * @author Dana Barghouthi
 *
 */
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String password;
	private String message;
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * validate login
	 *
	 * @return
	 */
	public String validateUsernamePassword() {
		boolean valid = validate(user, password);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "statementsView?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	/**
	 * check if the enterd users valids
	 *
	 * @param user
	 * @param password
	 * @return
	 */
	private boolean validate(String user, String password) {
		if (user.equals("user")) {
			return password.equals("user");
		} else if (user.equals("admin")) {
			return password.equals("admin");
		}
		return false;

	}

	/**
	 * logout event to invalidate session
	 *
	 * @return
	 */
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login?faces-redirect=true";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
