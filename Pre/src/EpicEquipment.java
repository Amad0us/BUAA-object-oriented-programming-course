public class EpicEquipment extends Equipment {
    
    public EpicEquipment(int equId, String equName, int equStar, long price) {
        super(equId, equName, equStar, price);
    }
    
    private double ratio;
    
    public void putRatio(double ratio) {
        this.ratio = ratio;
    }
    
    public String getClassName() {
        return "EpicEquipment";
    }
    
    public double getRatio() {
        return ratio;
    }
}
