public class RecoverBottle extends Bottle {
    
    public RecoverBottle(int botId, String botName, int botCapacity, long price) {
        super(botId, botName, botCapacity, price);
    }
    
    private double ratio;
    
    public void putRatio(double ratio) {
        this.ratio = ratio;
    }
    
    public String getClassName() {
        return "RecoverBottle";
    }
    
    public double getRatio() {
        return ratio;
    }
}
