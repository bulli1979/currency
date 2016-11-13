import ch.study.currency.action.NumberCalculation;
import static org.junit.Assert.*;
import org.junit.Test;
public class CalculateTest {
	private final double DEVIATION = 0.0001;
	@Test
	public void calculateTest(){
		double from = 1;
		double to = 1.2;
		assertEquals(1.2, NumberCalculation.change(from, to, 1) , DEVIATION);
		from = 0.8;
		to = 7.1;
		assertEquals(8.875, NumberCalculation.change(from, to, 1) , DEVIATION);
		from = 0.001;
		to = 1000;
		assertEquals(1000000, NumberCalculation.change(from, to, 1) , DEVIATION);
	}
	
}
