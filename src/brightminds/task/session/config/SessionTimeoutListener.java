package brightminds.task.session.config;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * this class to decied how the system will deal with session timeout
 *
 * @author Dana Barghouthi
 *
 */
public class SessionTimeoutListener implements PhaseListener {
	/**
	 *
	 */
	private static final long serialVersionUID = -7041073941805264147L;
	private static final String LOGIN_PAGE = "login.xhtml";

	private String getLoginPath() {
		return LOGIN_PAGE;
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.getPartialViewContext().isAjaxRequest()
				|| facesContext.getRenderResponse()) {
			return;
		}

		final HttpServletRequest request = HttpServletRequest.class
				.cast(facesContext.getExternalContext().getRequest());
		if (request.getDispatcherType() == DispatcherType.FORWARD
				&& getLoginPath().equals(request.getServletPath())) {
			final String redirect = facesContext.getExternalContext()
					.getRequestContextPath() + request.getServletPath();
			try {
				facesContext.getExternalContext().redirect(redirect);
			} catch (final IOException e) {
				Logger.getLogger(SessionTimeoutListener.class)
						.error(e.getMessage());
			}
		}
	}

	@Override
	public void afterPhase(final PhaseEvent event) {
		// no-op
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}