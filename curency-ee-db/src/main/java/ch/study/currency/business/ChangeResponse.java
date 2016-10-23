package ch.study.currency.business;

public class ChangeResponse {
	public int status;
	public String from;
	public String to;
	public double value;
	public double result;
	
	public ChangeResponse(Builder builder){
		this.from = builder.from;
		this.to= builder.to;
		this.value = builder.value;
		this.result = builder.result;
		this.status = builder.status;
	}
	
	public static class Builder{
		public int status;
		public String from;
		public String to;
		public double value;
		public double result;
		
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
		
		public ChangeResponse build(){
			return new ChangeResponse(this);
		}
		
	}
	
	
	
}
