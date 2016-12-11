package ch.study.currency.business;

/**
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * The ChangeResponse class provides a uniform format pattern for response objects.
 *
 */
public class ChangeResponse {
	private int status;
	private String from;
	private String to;
	private double value;
	private double result;
	private String error;
	
	/**
	 * Constructor to build a ChangeResponse object.
	 * 
	 * @param builder	Builder object to commit
	 * 
	 */
	public ChangeResponse(Builder builder){
		this.from = builder.from;
		this.to= builder.to;
		this.value = builder.value;
		this.result = builder.result;
		this.status = builder.status;
		this.error = builder.error;
	}
	
	
	/**
	 * The static Builder class supports to better handling of all input values in a constructor.
	 * Provides better readability. (For better understanding, see "Effective Java", Item 2)
	 */
	public static class Builder{
		private int status;
		private String from;
		private String to;
		private double value;
		private double result;
		private String error;
		
		/**
		 * Constructor to build a Builder object with the commited values.	
		 * 
		 * @param from 		base currency rate
		 * @param to 		target currency rate
		 * @param value 	base currency value
		 * 
		 */
		public Builder(String from,String to,double value){
			this.from = from;
			this.to= to;
			this.value =value;
		}
		
		/**
		 * Adds a result value to the Builder object.
		 * 
		 * @param result
		 * 
		 * @return a Builder object
		 * 
		 */
		public Builder addResult(double result){
			this.result = result;
			return this;
		} 
		
		/**
		 * Adds a status value to the Builder object.
		 * 
		 * @param status 	
		 * 
		 * @return a Builder object
		 * 
		 */
		public Builder addStatus(int status){
			this.status = status;
			return this;
		} 
		
		/**
		 * Adds an error string to the Builder object. 
		 * 
		 * @param error 	error string
		 * 
		 * @return a Builder object
		 * 
		 */
		public Builder addError(String error){
			this.error = error;
			return this;
		} 
		
		/**
		 * Builds a ChangeResponse object from the Builder object this method is invoked.
		 * 
		 * @return new ChangeResponse Object
		 * 
		 */
		public ChangeResponse build(){
			return new ChangeResponse(this);
		}
		
	}

	/**
	 * Returns the value in the "status" field of the Builder Object.
	 * 
	 * @return "status" value
	 * 
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Returns the value in the "from" field of the Builder Object.
	 * 
	 * @return "from" value
	 * 
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Returns the value in the "to" field of the Builder Object.
	 * 
	 * @return "to" value
	 * 
	 */
	public String getTo() {
		return to;
	}

	
	/**
	 * Returns the value in the "value" field of the Builder Object.
	 * 
	 * @return "value" value
	 * 
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Returns the value in the "result" field of the Builder Object.
	 * 
	 * @return "result" value
	 * 
	 */
	public double getResult() {
		return result;
	}

	/**
	 * Returns the value in the "error" field of the Builder Object.
	 * 
	 * @return "error" value
	 * 
	 */
	public String getError() {
		return error;
	}
	
	
	
}
