import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeTrans implements Comparable<TradeTrans> {
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:ss");
	private LocalDateTime tradeTime;
	private char tradeType;
	private int tradeVol;
	private double tradePrice;
	private double tradeTotal;
	
	public TradeTrans(String dd, String ti, char type, int vo, double pr, double tt) {
		tradeTime = LocalDateTime.parse(dd+ti, DTF);
		tradeType = type;
		tradeVol = vo;
		tradePrice = pr;
		tradeTotal = tt;
	}
	
	@Override
	public int compareTo(TradeTrans that) {
		return this.tradeTime.compareTo(that.tradeTime);
	}
	
	@Override
	public boolean equals(Object that) {
		return this == that ||
				(that instanceof TradeTrans && this.compareTo((TradeTrans) that) == 0);
	}
	
	@Override
	public int hashCode() {
		return this.tradeTime.hashCode();
	}
	
	public LocalDateTime getTradeTime() {
		return tradeTime;
	}
	
	public char getTradeType() {
		return tradeType;
	}
	
	public int getTradeVol() {
		return tradeVol;
	}
	
	public double getTradeTotal() {
		return tradeTotal;
	}
	
	public String toString() {
		return (tradeTime.toString() + "\t" + tradeType + "\t" + tradeVol + "\t" + tradePrice);
	}
}
