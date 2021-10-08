package brightminds.task.model;

/**
 *
 * @author Dana Barghouthi
 *
 */
public class Account {
	private int id;
	private String accountType;
	private String accountNumber;

	public Account(int id, String accountType, String accountNumber) {
		super();
		this.id = id;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
	}

	public Account() {
		super();
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Account id=" + id + ", accountType=" + accountType
				+ ", accountNumber=" + accountNumber;
	}

}
