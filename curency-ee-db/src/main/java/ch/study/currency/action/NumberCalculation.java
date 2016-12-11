package ch.study.currency.action;


/**
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * The NumberCalculation class provides the necessary basic math operation to
 * convert an initial value with specific currency to a target value with a specific currency. 
 * Furthermore it provides an operation to calculate the ratio between two currencies.
 * 
 */
public class NumberCalculation {
	
	/**
	 * Calculates the currency amount of the input value.
	 * 
	 * @param from 		base currency rate
	 * @param to 		target currency rate
	 * @param value 	initial currency value
	 * 
	 * @return 	target currency value
	 */
	public static double change(double from,double to,double value){
		return 1/from * to * value;
	}
	
	
	/** 
	 * Returns the ratio between "from" and "to" value
	 * 
	 * @param from 	base currency rate
	 * @param to 	target currency rate
	 * 
	 * @return ratio between initial and target currency rate
	 */
	public static double getRelation(double from,double to){
		return 1/from * to;
	}
}
