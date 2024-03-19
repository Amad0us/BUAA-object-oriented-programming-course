import java.util.ArrayList;

public class Store {
    
    private static ArrayList<Bottle> bottlePurchaseRecord = new ArrayList<>();
    private static ArrayList<Equipment> equipPurchaseRecord = new ArrayList<>();
    private static ArrayList<Food> foodPurchaseRecord = new ArrayList<>();
    
    public Store() {
    
    }
    
    public static int getPriceAverage(int mark) {
        double sum = 0;
        if (mark == 1) {
            for (Bottle bottle : bottlePurchaseRecord) {
                sum += bottle.getPrice();
            }
            sum /= bottlePurchaseRecord.size();
        } else if (mark == 2) {
            for (Equipment equipment : equipPurchaseRecord) {
                sum += equipment.getPrice();
            }
            sum /= equipPurchaseRecord.size();
        } else if (mark == 3) {
            for (Food food : foodPurchaseRecord) {
                sum += food.getPrice();
            }
            sum /= foodPurchaseRecord.size();
        }
        sum = Math.floor(sum);
        return (int) sum;
    }
    
    public static int getAverage(int mark) {
        double sum = 0;
        if (mark == 1) {
            for (Bottle bottle : bottlePurchaseRecord) {
                sum += bottle.getOriginalCapacity();
            }
            sum /= bottlePurchaseRecord.size();
        } else if (mark == 2) {
            for (Equipment equipment : equipPurchaseRecord) {
                sum += equipment.getEqu_star();
            }
            sum /= equipPurchaseRecord.size();
        } else if (mark == 3) {
            for (Food food : foodPurchaseRecord) {
                sum += food.getFood_Energy();
            }
            sum /= foodPurchaseRecord.size();
        }
        sum = Math.floor(sum);
        return (int) sum;
    }
    
    public static ArrayList<Bottle> getBottlePurchaseRecord() {
        return bottlePurchaseRecord;
    }
    
    public static ArrayList<Equipment> getEquipPurchaseRecord() {
        return equipPurchaseRecord;
    }
    
    public static ArrayList<Food> getFoodPurchaseRecord() {
        return foodPurchaseRecord;
    }
}
