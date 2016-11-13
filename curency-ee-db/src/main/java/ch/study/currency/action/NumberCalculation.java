package ch.study.currency.action;

public class NumberCalculation {
	
	/**Klasse stellt eine Ergebnis für einen eingabe Wert her
	 * Ich gebe von zu in relation zu einer Basis und bekomme ein Ergebnis. 
	 * @param from Ausgangswert
	 * @param to Zielwert
	 * @param value Anzahl Einheiten
	 * @return Wieviel von Zielwert ergibt Ausgangswert
	 */
	public static double change(double from,double to,double value){
		return 1/from * to * value;
	}
	
	
	/** Gibt eine relation 2er Werte zurück
	 * @param from Ausgangswert
	 * @param to Zielwert
	 * @return
	 */
	public static double getRelation(double from,double to){
		return 1/from * to;
	}
}
