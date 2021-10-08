package brightminds.task.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import brightminds.task.dao.imp.StatementDao;
import brightminds.task.model.Statement;
import brightminds.task.session.util.SessionUtils;

@ManagedBean
@ViewScoped
/**
 * this class is a managedbean controller which have all funcionality used in
 * the statements page
 *
 * @author Dana Barghouthi
 *
 */
public class StatementsController {
	private List<Statement> statements = new ArrayList<>();
	private LocalDate fromDate;
	private LocalDate toDate;
	private double fromAmount;
	private double toAmount;
	private int accountId;
	private StatementDao dao = new StatementDao();

	@PostConstruct
	public void init() {
		statements = dao.getLastThreeMonth();
	}

	/**
	 * initialize data
	 */
	public void initData() {
		fromDate = null;
		toDate = null;
		fromAmount = 0;
		toAmount = 0;
		accountId = 0;
	}

	/**
	 * search functionality to fill statements list
	 */
	public void search() {
		statements = new ArrayList<>();
		if (accountId != 0) {
			statements = dao.getByAccountId(accountId);
		} else if (!Objects.isNull(fromDate) && !Objects.isNull(toDate)) {
			statements = dao.getStatementFromDateToDate(fromDate, toDate);
		} else if (fromAmount != 0 && toAmount != 0) {
			statements = dao.getStatementFromAmountToAmount(fromAmount,
					toAmount);
		} else {
			statements = dao.getLastThreeMonth();
		}
	}

	/**
	 * check if current user is admin
	 *
	 * @return
	 */
	public boolean haveAdminPermission() {
		return SessionUtils.getPermissions().equals("admin");
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public double getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(double fromAmount) {
		this.fromAmount = fromAmount;
	}

	public double getToAmount() {
		return toAmount;
	}

	public void setToAmount(double toAmount) {
		this.toAmount = toAmount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
