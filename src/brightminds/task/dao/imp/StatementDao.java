package brightminds.task.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import brightminds.task.config.MsAccessDbConfig;
import brightminds.task.constance.ColumnsNames;
import brightminds.task.dao.Dao;
import brightminds.task.model.Account;
import brightminds.task.model.Statement;

/**
 * Statement DAO have all functionality needed on db layer
 *
 * @author Dana Barghouthi
 *
 */
public class StatementDao implements Dao<Statement> {

	private List<Statement> statements = new ArrayList<>();// have list of
															// selected
															// statements
	private static final String DATE_FORMAT = "dd.MM.yyyy";
	private static final String SELECT_STATEMENT_QUERY = "SELECT * FROM Statement , Account where Statement.account_id = Account.id  ";// general
	// query
	// resued
	// many
	// time
	// to
	// select
	// all
	// statement
	// from
	// db

	@Override
	public Optional<Statement> get(int id) {
		Statement newStatement = null;
		if (!Objects.isNull(statements) && !statements.isEmpty()) {
			newStatement = statements.stream().filter(s -> id == s.getId())
					.findFirst().orElse(null);
		}
		if (Objects.isNull(newStatement)) {
			Connection connection = MsAccessDbConfig.connection();
			String sql = SELECT_STATEMENT_QUERY + " AND Statement.id= " + id;

			try (java.sql.Statement statement = connection.createStatement()) {
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					int accountId = result.getInt(ColumnsNames.ID);
					String accountNumber = result
							.getString(ColumnsNames.ACCOUNT_NUMBER);
					String accountType = result
							.getString(ColumnsNames.ACCOUNT_TYPE);
					Account newAccount = new Account(accountId, accountType,
							accountNumber);
					String datefeild = result
							.getString(ColumnsNames.DATE_FIELD);
					DateTimeFormatter formatter = DateTimeFormatter
							.ofPattern(DATE_FORMAT);
					LocalDate localDate = LocalDate.parse(datefeild, formatter);
					String amount = result.getString(ColumnsNames.AMOUNT);
					double amountValue = StringUtils.isNotBlank(amount)
							? Double.parseDouble(amount)
							: 0.0;
					newStatement = new Statement(id, newAccount, localDate,
							amountValue);
				}

			} catch (Exception e) {
				Logger.getLogger(StatementDao.class).error(e.getMessage());
			}
		}
		statements = new ArrayList<>();
		statements.add(newStatement);
		return Optional.ofNullable(newStatement);
	}

	@Override
	public List<Statement> getAll() {
		Connection connection = MsAccessDbConfig.connection();
		statements = new ArrayList<>();
		String sql = SELECT_STATEMENT_QUERY;
		Statement newStatement = new Statement();
		try (java.sql.Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt(ColumnsNames.ID);
				int accountId = result.getInt(ColumnsNames.ID);
				String accountNumber = result
						.getString(ColumnsNames.ACCOUNT_NUMBER);
				String accountType = result
						.getString(ColumnsNames.ACCOUNT_TYPE);
				Account newAccount = new Account(accountId, accountType,
						accountNumber);
				String datefeild = result.getString(ColumnsNames.DATE_FIELD);
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern(DATE_FORMAT);
				LocalDate localDate = LocalDate.parse(datefeild, formatter);
				String amount = result.getString(ColumnsNames.AMOUNT);
				double amountValue = StringUtils.isNotBlank(amount)
						? Double.parseDouble(amount)
						: 0.0;
				newStatement = new Statement(id, newAccount, localDate,
						amountValue);
				statements.add(newStatement);
			}
		} catch (Exception e) {
			Logger.getLogger(StatementDao.class).error(e.getMessage());
		}
		return statements;
	}

	/**
	 * get all statements for last three months
	 *
	 * @return
	 */
	public List<Statement> getLastThreeMonth() {
		statements = getAll();
		List<Statement> filterStatements = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement.getDatefield()
					.isAfter(LocalDate.now().minusMonths(3).minusDays(1))
					&& statement.getDatefield()
							.isBefore(LocalDate.now().plusDays(1))) {
				filterStatements.add(statement);
			}
		}
		statements = filterStatements;
		return statements;
	}

	/**
	 * get statement from amount to amount
	 *
	 * @param fromAmount
	 * @param toAmount
	 * @return
	 */
	public List<Statement> getStatementFromAmountToAmount(double fromAmount,
			double toAmount) {
		statements = getAll();
		List<Statement> filterStatements = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement.getAmount() >= fromAmount
					&& statement.getAmount() <= toAmount) {
				filterStatements.add(statement);
			}
		}
		statements = filterStatements;
		return statements;
	}

	/**
	 * get statement from date to date
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<Statement> getStatementFromDateToDate(LocalDate fromDate,
			LocalDate toDate) {
		statements = getAll();
		List<Statement> filterStatements = new ArrayList<>();
		for (Statement statement : statements) {
			if (statement.getDatefield().isAfter(fromDate.minusDays(1))
					&& statement.getDatefield().isBefore(toDate.plusDays(1))) {
				filterStatements.add(statement);
			}
		}
		statements = filterStatements;
		return statements;
	}

	/**
	 * get statement by account id
	 *
	 * @param accountId
	 * @return
	 */
	public List<Statement> getByAccountId(int accountId) {
		Statement newStatement = null;
		statements = new ArrayList<>();
		Connection connection = MsAccessDbConfig.connection();
		String sql = SELECT_STATEMENT_QUERY + " AND Statement.account_id= "
				+ accountId;

		try (java.sql.Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt(ColumnsNames.ID);
				String datefeild = result.getString(ColumnsNames.DATE_FIELD);
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern(DATE_FORMAT);
				LocalDate localDate = LocalDate.parse(datefeild, formatter);
				String amount = result.getString(ColumnsNames.AMOUNT);
				double amountValue = StringUtils.isNotBlank(amount)
						? Double.parseDouble(amount)
						: 0.0;

				String accountNumber = result
						.getString(ColumnsNames.ACCOUNT_NUMBER);
				String accountType = result
						.getString(ColumnsNames.ACCOUNT_TYPE);
				Account newAccount = new Account(accountId, accountType,
						accountNumber);
				newStatement = new Statement(id, newAccount, localDate,
						amountValue);
				statements.add(newStatement);
			}
		} catch (Exception e) {
			Logger.getLogger(StatementDao.class).error(e.getMessage());
		}

		return statements;

	}

	@Override
	public void save(Statement statement) {
		// not needed for the task
	}

	@Override
	public void update(Statement statement, String[] params) {
		// not needed for the task
	}

	@Override
	public void delete(Statement statement) {
		// not needed for the task
	}
}