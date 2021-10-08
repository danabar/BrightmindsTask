package brightminds.task.model;

import java.time.LocalDate;

/**
 *
 * @author Dana Barghouthi
 *
 */
public class Statement {
	private int id;
	private Account account;
	private LocalDate datefield;
	private double amount;

	public Statement() {
		super();
	}

	public Statement(int id, Account account, LocalDate datefield,
			double amount) {
		super();
		this.id = id;
		this.account = account;
		this.datefield = datefield;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDatefield() {
		return datefield;
	}

	public void setDatefield(LocalDate datefield) {
		this.datefield = datefield;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Statement id=" + id + ", account=" + account + ", datefield="
				+ datefield + ", amount=" + amount;
	}

}
