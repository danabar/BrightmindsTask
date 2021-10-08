import java.time.LocalDate;

import brightminds.task.dao.imp.StatementDao;

public class Test {

	public static void main(String[] args) {
		StatementDao dao = new StatementDao();
		dao.getAll();

		dao.getLastThreeMonth();

		dao.getByAccountId(3);

		dao.getStatementFromAmountToAmount(200, 300);
		dao.getStatementFromDateToDate(LocalDate.parse("2020-09-01"),
				LocalDate.parse("2020-11-01"));

	}

}
