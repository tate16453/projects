import java.util.Objects;

/**
 *
 * @author tatewongweeratorn
 */
public class ShareOne {
    private String name;
    private String action;  
    private double costAverage;
    private double price;
    private double totShare;
    
    
    public ShareOne(String name, String action, double price, double totShare) {
        this.name = name;
        this.action = action;
        this.price = price;
        this.totShare = totShare;
    }
    
    
}
