public class Equipment implements Commodity {
    private int equId;
    private String equName;
    private int equStar;
    private long price;
    
    @Override
    public long getPrice() {
        return price;
    }
    
    public int getEqu_id() {
        return equId;
    }
    
    public String getEqu_name() {
        return equName;
    }
    
    public int getEqu_star() {
        return equStar;
    }
    
    public void star_rise() {
        equStar++;
    }
    
    public Equipment(int equId, String equName, int equStar, long price) {
        this.equId = equId;
        this.equName = equName;
        this.equStar = equStar;
        this.price = price;
    }
}
