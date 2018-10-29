import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Share {
	private String name;
	private ArrayList<TradeTrans> listTrans;
	private double avgCost;
	private int curBalance;
	
	public Share(String n) {
		name = n;
		listTrans = new ArrayList<>(0);
	}
	
	public void addTrans(TradeTrans t) {
		listTrans.add(0, t);
	}
	
	public void calculateTransation() {
		avgCost = 0.0;
		curBalance = 0;
		
		Collections.sort(listTrans);
		
		for (Iterator<TradeTrans> it = listTrans.iterator(); it.hasNext();) {
			TradeTrans tt = it.next();
			if (tt.getTradeType() == 'B') {
				double s = (avgCost * curBalance) + tt.getTradeTotal();
				curBalance += tt.getTradeVol();
				avgCost = s / curBalance;		
			} else {
				curBalance -= tt.getTradeVol();
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public double getAvgCost() {
		return avgCost;
	}
	
	public int getCurBalance() {
		return curBalance;
	}
	
	public int getTransSize() {
		return listTrans.size();
	}
	
	public void printTrans() {
		System.out.println("");
		System.out.println(name);

		Iterator<TradeTrans> it = listTrans.iterator();
		for (int i = 0; i < listTrans.size(); ++i) {
			TradeTrans tt = it.next();
			System.out.println((i+1) + "\t" + tt.toString());
		}
	}
}
