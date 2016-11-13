package ch.study.currency.business;

/**Change Response
 * contains Builder Class and some Stuff give the possibility to has the same format for requests
 * @author admin
 *
 */
public class ChangeResponse {
	private int status;
	private String from;
	private String to;
	private double value;
	private double result;
	private String error;
	public ChangeResponse(Builder builder){
		this.from = builder.from;
		this.to= builder.to;
		this.value = builder.value;
		this.result = builder.result;
		this.status = builder.status;
		this.error = builder.error;
	}
	
	public static class Builder{
		private int status;
		private String from;
		private String to;
		private double value;
		private double result;
		private String error;
		
		public Builder(String from,String to,double value){
			this.from = from;
			this.to= to;
			this.value =value;
		}
		
		public Builder addResult(double result){
			this.result = result;
			return this;
		} 
		
		public Builder addStatus(int status){
			this.status = status;
			return this;
		} 
		
		public Builder addError(String error){
			this.error = error;
			return this;
		} 
		
		public ChangeResponse build(){
			return new ChangeResponse(this);
		}
		
	}

	public int getStatus() {
		return status;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public double getValue() {
		return value;
	}

	public double getResult() {
		return result;
	}

	public String getError() {
		return error;
	}
	
	
	
}
