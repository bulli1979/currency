import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;

public class TestCurrencyReflection {
	
	@Test
	public void TestInterfaceReflection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		List<Currency> currencyList = CurrencyData.INSTANCE.getCurrencyList();
		assertTrue(currencyList.size()>0);
	}
	
}
