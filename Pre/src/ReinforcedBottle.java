public class ReinforcedBottle extends Bottle {
    public ReinforcedBottle(int botId, String botName, int botCapacity, long price) {
        super(botId, botName, botCapacity, price);
    }
    
    private double ratio;
    
    public void putRatio(double ratio) {
        this.ratio = ratio;
    }
    
    public double getRatio() {
        return ratio;
    }
    
    public String getClassName() {
        return "ReinforcedBottle";
    }
}
