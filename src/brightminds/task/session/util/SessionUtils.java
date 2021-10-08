package brightminds.task.session.util;

import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * This class provide all session functionality needed
 *
 * @author Dana Barghouthi
 *
 */
public class SessionUtils {
	private static final String USERNAME = "username";

	private SessionUtils() {
		super();
	}

	/**
	 * get current session
	 *
	 * @return
	 */
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	/**
	 * get servlet request
	 *
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	/**
	 * get session active user name
	 *
	 * @return
	 */
	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute(USERNAME).toString();
	}

	/**
	 * get session active user id
	 *
	 * @return
	 */
	public static String getUserId() {
		HttpSession session = getSession();
		if (!Objects.isNull(session)) {
			return (String) session.getAttribute("userid");
		}
		return null;

	}

	/**
	 * get permissions the active user have
	 *
	 * @return
	 */
	public static String getPermissions() {
		HttpSession session = getSession();
		if (!Objects.isNull(session)
				&& !Objects.isNull(session.getAttribute(USERNAME))) {
			return session.getAttribute(USERNAME).toString();
		}
		return StringUtils.EMPTY;
	}
}
