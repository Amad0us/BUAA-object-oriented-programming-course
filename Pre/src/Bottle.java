public class Bottle implements Commodity {
    private int botId;
    private String botName;
    private int botCapacity;
    private int originalCapacity;
    private long price;
    private int isempty = 1;
    
    public int getIsempty() {
        return isempty;
    }
    
    public void changeIsEmpty() {
        isempty = 0;
    }
    
    @Override
    public long getPrice() {
        return price;
    }
    
    public int getBot_id() {
        return botId;
    }
    
    public String getBot_name() {
        return botName;
    }
    
    public int getBot_capacity() {
        return botCapacity;
    }
    
    public Bottle(int botId, String botName, int botCapacity, long price) {
        this.botId = botId;
        this.botName = botName;
        this.botCapacity = botCapacity;
        this.originalCapacity = botCapacity;
        this.price = price;
    }
    
    public void bottle_to_0() {
        botCapacity = 0;
    }
    
    public void change_bottle(int botCap) {
        botCapacity -= botCap;
    }
    
    public int getOriginalCapacity() {
        return originalCapacity;
    }
    
}
