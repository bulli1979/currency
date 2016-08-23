package ch.study.currency;
/**
 * Enum as Singleton to have all courses in one place
 * stores the cursses of all courses permantently 
 * */
public enum CurrencyData{
	INSTANCE;
	
	private float chf=0;
	private float usd=0;
	/**
	 * @return the chf
	 */
	public float getChf() {
		return chf;
	}
	/**
	 * @param chf the chf to set
	 */
	public void setChf(float chf) {
		this.chf = chf;
	}
	/**
	 * @return the usd
	 */
	public float getUsd() {
		return usd;
	}
	/**
	 * @param usd the usd to set
	 */
	public void setUsd(float usd) {
		this.usd = usd;
	}
}
