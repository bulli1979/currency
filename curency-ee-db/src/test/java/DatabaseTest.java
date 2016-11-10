import java.sql.SQLException;

import ch.study.currency.data.DAOCurrency;
import static org.junit.Assert.*;
import org.junit.Test;

public class DatabaseTest {
	@Test
	public void testCheckDayQuery() throws ClassNotFoundException, SQLException{
		boolean check = DAOCurrency.getInstance().checkDayCurrency();
		assertFalse(check);
	}
}
