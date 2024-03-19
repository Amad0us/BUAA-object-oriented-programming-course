public class CritEquipment extends Equipment {
    public CritEquipment(int equId, String equName, int equStar, long price) {
        super(equId, equName, equStar, price);
    }
    
    private int critical;
    
    public void putCritical(int critical) {
        this.critical = critical;
    }
    
    public String getClassName() {
        return "CritEquipment";
    }
    
    public int getCritical() {
        return critical;
    }
}
