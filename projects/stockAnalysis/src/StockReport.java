/*
 * Take all the data from the lists (data-1.txt, data-2.txt) and sort them out according to dates
 *  SortedSet -> TreeSet + Comparator String
 *  boolean equals()
 *
 * Create an object for each of the shares and organize all the action (B/S) according to dates
 *
 * Calculate the amount of shares acquisition and the total expense in the ascending order of dates
 *
 * Calculate the average share acquisition in consequtive order
 *
 * According to the average share acquisition, calculate the current standing of the amount of money
 * to figured out profit/loss
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author tatewongweeratorn
 */
public class StockReport {
    private static final String DATA = "data-1.txt";
    static final Map<String, Share> listShares = new HashMap<>(0);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
          
        StockReport sr = new StockReport();
        
        for (int i = 0; i < args.length; ++i) {
            processFile(args[i], sr);
        }
        
        
        Set<String> keys = listShares.keySet();
        
        System.out.println("-Analysis-" + "\n");
        System.out.println(String.format("%15s%20s%20s", "Name:", "Balance:", "Average Cost:"));
        
        //DecimalFormat realFormatter; // DecimalFormat function
        //realFormatter = new DecimalFormat("0.000"); // Four decimal places format
        
        for (String k : keys) {
            Share share = listShares.get(k);
            share.calculateTransation();
            //System.out.println(share.getName() + "\t\t" + share.getCurBalance() + "\t\t" + realFormatter.format(share.getAvgCost()));
            if (share.getCurBalance() != 0) {
                System.out.println(String.format("%15s%,20d%,20.3f", share.getName(), share.getCurBalance(), share.getAvgCost()));
            }
        }        
    }
    
    static void processFile(String filename, StockReport sr) {     
        try {
            FileReader fileReader = new FileReader(filename); //input file
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) { //read file in line by line
                String array[] = line.split("\\t");
                sr.processTradeTrans(array[0], array[1], array[2], array[3], array[4], array[5], array[9]);
                //System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + filename);
            System.exit(1);
        }
    }
    
    public void processTradeTrans(String aDate, String aTime, String aType, String aName, String aVol, String aPrice, String aTotal) {

		Share share;

		if(listShares.containsKey(aName)) {
			share = listShares.get(aName);
		} else {
			share = new Share(aName);
			listShares.put(aName, share);
		}

		TradeTrans tt = new TradeTrans(
				aDate,
				aTime,
				aType.charAt(0), 
				Integer.parseInt(aVol.replace(",", "")),
				Double.parseDouble(aPrice.replace(",", "")), 
				Double.parseDouble(aTotal.replace(",", ""))
				);           

		share.addTrans(tt);
	}
    
    private static boolean readShare() {
        try {
            File f = new File(DATA);
            Scanner sc = new Scanner(f).useDelimiter("\\n"); 
        }
        catch (FileNotFoundException ex) 
        {
            System.err.println("FileNotFoundException: " + ex.getMessage());
            return false;
        }
        return false;
    }

}
