package brightminds.task.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;

import brightminds.task.config.MSAccessConfig;
import brightminds.task.constance.TablesAndColumnsNames;
import brightminds.task.dao.Dao;
import brightminds.task.model.Account;

/**
 * This class have all Account functionality to deal with database
 *
 * @author Dana Barghouthi
 *
 */
public class AccountDao implements Dao<Account> {

	private List<Account> accounts = new ArrayList<>();

	@Override
	public Optional<Account> get(int id) {
		Account newAccount = null;
		if (!Objects.isNull(accounts) && !accounts.isEmpty()) {
			newAccount = accounts.stream().filter(s -> id == s.getId())
					.findFirst().orElse(null);
		}
		if (Objects.isNull(newAccount)) {
			Connection connection = MSAccessConfig.connection();
			String sql = "SELECT * FROM Account where id= " + id;

			try (java.sql.Statement statement = connection.createStatement()) {
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {

					String accountNumber = result
							.getString(TablesAndColumnsNames.ACCOUNT_NUMBER);
					String accountType = result
							.getString(TablesAndColumnsNames.ACCOUNT_TYPE);
					newAccount = new Account(id, accountType, accountNumber);
				}

			} catch (Exception e) {
				Logger.getLogger(AccountDao.class).error(e.getMessage());
			}
		}
		return Optional.ofNullable(newAccount);
	}

	@Override
	public List<Account> getAll() {
		Account newAccount;
		Connection connection = MSAccessConfig.connection();
		String sql = "SELECT * FROM Account";
		try (java.sql.Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt(TablesAndColumnsNames.ID);
				String accountNumber = result
						.getString(TablesAndColumnsNames.ACCOUNT_NUMBER);
				String accountType = result
						.getString(TablesAndColumnsNames.ACCOUNT_TYPE);
				newAccount = new Account(id, accountType, accountNumber);
				accounts.add(newAccount);
			}

		} catch (Exception e) {
			Logger.getLogger(AccountDao.class).error(e.getMessage());
		}
		return accounts;
	}

	@Override
	public void save(Account account) {
		// not needed for the task
	}

	@Override
	public void update(Account account, String[] params) {
		// not needed for the task
	}

	@Override
	public void delete(Account account) {
		// not needed for the task
	}
}