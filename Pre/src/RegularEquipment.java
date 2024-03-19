public class RegularEquipment extends Equipment {
    public RegularEquipment(int equId, String equName, int equStar, long price) {
        super(equId, equName, equStar, price);
    }
    
    public String getClassName() {
        return "RegularEquipment";
    }
}
