package brightminds.task.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import brightminds.task.dao.imp.StatementDao;
import brightminds.task.model.Statement;

class StatementTest {

	@Test
	void testGetLastThreeMonth() {
		StatementDao dao = new StatementDao();
		List<Statement> statements = dao.getLastThreeMonth();
		for (Statement statement : statements) {
			assertTrue(statement.getDatefield()
					.isAfter(LocalDate.now().minusMonths(3).minusDays(1))
					&& statement.getDatefield()
							.isBefore(LocalDate.now().plusDays(1)));
		}
	}

	@Test
	void testGetFromAmountToAmount() {
		double fromAmount = 100;
		double toAmount = 200;
		StatementDao dao = new StatementDao();
		List<Statement> statements = dao
				.getStatementFromAmountToAmount(fromAmount, toAmount);
		for (Statement statement : statements) {
			assertTrue(statement.getAmount() >= fromAmount
					&& statement.getAmount() <= toAmount);

		}

	}

	@Test
	void testGetFromDateToDate() {
		LocalDate fromDate = LocalDate.of(2020, 9, 1);
		LocalDate toDate = LocalDate.of(2020, 11, 1);
		StatementDao dao = new StatementDao();
		List<Statement> statements = dao.getStatementFromDateToDate(fromDate,
				toDate);
		for (Statement statement : statements) {
			assertTrue(statement.getDatefield().isAfter(fromDate.minusDays(1))
					&& statement.getDatefield().isBefore(toDate.plusDays(1)));
		}
	}

	@Test
	void testGetByAccountId() {
		StatementDao dao = new StatementDao();
		List<Statement> statements = dao.getByAccountId(3);
		for (Statement statement : statements) {
			assertTrue(statement.getAccount().getId() == 3);

		}
	}

}
