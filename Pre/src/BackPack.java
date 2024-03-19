import java.util.HashMap;
import java.util.TreeMap;

public class BackPack {
    private HashMap<String, TreeMap<Integer, Bottle>> bottleHashMap = new HashMap<>();//药水统计
    private HashMap<String, TreeMap<Integer, Equipment>> equipmentHashMap = new HashMap<>();//装备统计
    private HashMap<String, TreeMap<Integer, Food>> foodHashMap = new HashMap<>();//装备统计
    
    public BackPack() {
        this.bottleNumber = 0;
        this.equipmentNumber = 0;
        this.foodNumber = 0;
    }
    
    private int bottleNumber;
    private int equipmentNumber;
    private int foodNumber;
    
    public int get_bottle_number() {
        return bottleNumber;
    }
    
    public int get_equipment_number() {
        return equipmentNumber;
    }
    
    public int get_food_number() {
        return foodNumber;
    }
    
    public HashMap<String, TreeMap<Integer, Equipment>> get_equipmentHashMap() {
        return equipmentHashMap;
    }
    
    public HashMap<String, TreeMap<Integer, Bottle>> get_bottleHashMap() {
        return bottleHashMap;
    }
    
    public HashMap<String, TreeMap<Integer, Food>> get_foodHashMap() {
        return foodHashMap;
    }
    
    public void carry_bottle(int botId, String botName, int botCapacity, int max, long price) {
        Bottle bottle = new Bottle(botId, botName, botCapacity, price);
        if (!bottleHashMap.containsKey(botName)) {
            TreeMap<Integer, Bottle> tmpTree = new TreeMap<>();
            tmpTree.put(botId, bottle);
            bottleHashMap.put(botName, tmpTree);
            bottleNumber++;
        } else {
            TreeMap<Integer, Bottle> tmpTree = bottleHashMap.get(botName);
            if (tmpTree.size() < max) {
                tmpTree.put(botId, bottle);
                bottleNumber++;
            }
        }
    }
    
    public void unload_bottle(int botId, String botName) {
        if (bottleHashMap.containsKey(botName) && bottleHashMap.get(botName).containsKey(botId)) {
            bottleHashMap.get(botName).remove(botId);
            bottleNumber--;
        }
    }
    
    public void carry_equipment(int equId, String equName, int equStar, long price) {
        Equipment equipment = new Equipment(equId, equName, equStar, price);
        if (!equipmentHashMap.containsKey(equName)) {
            equipmentNumber++;
        }
        TreeMap<Integer, Equipment> tmpTree = new TreeMap<>();
        tmpTree.put(equId, equipment);
        get_equipmentHashMap().put(equName, tmpTree);
    }
    
    public void unload_equipment(String equName, int equId) {
        TreeMap<Integer, Equipment> tmpTree = equipmentHashMap.get(equName);
        if (equipmentHashMap.containsKey(equName) && tmpTree.containsKey(equId)) {
            equipmentHashMap.remove(equName);
            equipmentNumber--;
        }
    }
    
    public void carry_food(int foodId, String foodName, int foodEnergy, long price) {
        Food food = new Food(foodId, foodName, foodEnergy, price);
        if (!foodHashMap.containsKey(foodName)) {
            TreeMap<Integer, Food> tmpTree = new TreeMap<>();
            tmpTree.put(foodId, food);
            foodHashMap.put(foodName, tmpTree);
        } else {
            if (foodHashMap.get(foodName).containsKey(foodId)) {
                return;
            } else {
                TreeMap<Integer, Food> tmpTree = foodHashMap.get(foodName);
                tmpTree.put(foodId, food);
            }
        }
    }
    
    public void unload_food(int foodId, String foodName) {
        if (foodHashMap.containsKey(foodName) && foodHashMap.get(foodName).containsKey(foodId)) {
            foodHashMap.get(foodName).remove(foodId);
            foodNumber--;
        }
    }
    
    public int get_bottleLeast(String botName) {
        TreeMap<Integer, Bottle> tmpTree = bottleHashMap.get(botName);
        if (tmpTree != null && !get_bottleHashMap().get(botName).isEmpty()) {
            return tmpTree.firstKey();
        } else {
            return -1;
        }
    }
    
    public int use_bottle(String botName) {
        TreeMap<Integer, Bottle> tmpTree = bottleHashMap.get(botName);
        if (!bottleHashMap.containsKey(botName) || tmpTree.isEmpty()) {
            return -1;
        } else {
            int least = tmpTree.firstKey();
            if (tmpTree.get(least).getBot_capacity() >= 0) {
                int temp = tmpTree.get(least).getBot_capacity();
                tmpTree.get(least).bottle_to_0();
                return temp;
            } else {
                return 0;
            }
        }
    }
    
    public int get_foodLeast(String foodName) {
        TreeMap<Integer, Food> tmpTree = foodHashMap.get(foodName);
        if (tmpTree != null && !get_foodHashMap().get(foodName).isEmpty()) {
            return tmpTree.firstKey();
        } else {
            return -1;
        }
    }
    
    public int use_food(String foodName) {
        TreeMap<Integer, Food> tmpTree = foodHashMap.get(foodName);
        if (!foodHashMap.containsKey(foodName) || tmpTree.isEmpty()) {
            return -1;
        } else {
            int least = tmpTree.firstKey();
            return tmpTree.get(least).getFood_Energy();
        }
    }
}
