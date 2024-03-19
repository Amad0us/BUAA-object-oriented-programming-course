public class RegularBottle extends Bottle {
    public RegularBottle(int botId, String botName, int botCapacity, long price) {
        super(botId, botName, botCapacity, price);
    }
    
    public String getClassName() {
        return "RegularBottle";
    }
}
