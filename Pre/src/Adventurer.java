import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Adventurer implements Commodity {
    private int advId;
    private String advName;
    private int advHitPoint;
    private int advLevel;
    private long money;
    private ArrayList<String> attacks = new ArrayList<>();
    
    public ArrayList<String> get_attacks() {
        return attacks;
    }
    
    private ArrayList<String> attacked = new ArrayList<>();
    
    public ArrayList<String> get_attacked() {
        return attacked;
    }
    
    public String getAdvName() {
        return advName;
    }
    
    private BackPack advBackPack = new BackPack();
    private HashMap<Integer, Bottle> bottleHashMap = new HashMap<>();//药水统计
    private HashMap<Integer, Equipment> equipmentHashMap = new HashMap<>();//装备统计
    private HashMap<Integer, Food> foodHashMap = new HashMap<>();//装备统计
    private HashMap<Integer, Commodity> commodityHashMap = new HashMap<>();//价值体统计
    
    @Override
    public long getPrice() {
        long sum = money;
        for (Commodity commodity : commodityHashMap.values()) {
            sum += commodity.getPrice();
        }
        return sum;
    }
    
    public long getMoney() {
        return money;
    }
    
    public void changeMoney(long money) {
        this.money += money;
    }
    
    public int getCommoditySize() {
        return commodityHashMap.size();
    }
    
    private int bottleNumber;
    private int equipmentNumber;
    private int foodNumber;
    
    public Adventurer(int advId, String advName) {
        this.advId = advId;
        this.advName = advName;
        this.bottleNumber = 0;
        this.equipmentNumber = 0;
        this.foodNumber = 0;
        this.advLevel = 1;
        this.advHitPoint = 500;
        this.money = 0;
    }
    
    //创建药水瓶
    public void add_bottle(int botId, Bottle bottle) {
        bottleHashMap.put(botId, bottle);
        commodityHashMap.put(botId, bottle);
        bottleNumber++;
    }
    
    //创建装备
    public void add_equipment(int equId, Equipment equipment) {
        equipmentHashMap.put(equipment.getEqu_id(), equipment);
        commodityHashMap.put(equId, equipment);
        equipmentNumber++;
    }
    
    public void add_food(int foodId, Food food) {
        foodHashMap.put(food.getFood_Id(), food);
        commodityHashMap.put(foodId, food);
        foodNumber++;
    }
    
    //删除药水瓶
    public void delete_bottle(int bottleId) {
        bottleNumber--;
        String bottleName = get_bottleHashMap().get(bottleId).getBot_name();
        get_backpack().unload_bottle(bottleId, bottleName);
        bottleHashMap.remove(bottleId);
        commodityHashMap.remove(bottleId);
    }
    
    //删除装备
    public void delete_equipment(int equipmentId) {
        equipmentNumber--;
        String equipName = get_equipmentHashMap().get(equipmentId).getEqu_name();
        get_backpack().unload_equipment(equipName, equipmentId);
        equipmentHashMap.remove(equipmentId);
        commodityHashMap.remove(equipmentId);
    }
    
    public void delete_food(int foodId) {
        foodNumber--;
        String foodName = get_foodHashMap().get(foodId).getFood_name();
        get_backpack().unload_food(foodId, foodName);
        foodHashMap.remove(foodId);
        commodityHashMap.remove(foodId);
    }
    
    public int get_bottle_number() {
        return bottleNumber;
    }
    
    public int get_equipment_number() {
        return equipmentNumber;
    }
    
    public int get_food_number() {
        return foodNumber;
    }
    
    public int get_advLevel() {
        return advLevel;
    }
    
    public int get_advHitPoint() {
        return advHitPoint;
    }
    
    public HashMap<Integer, Equipment> get_equipmentHashMap() {
        return equipmentHashMap;
    }
    
    public HashMap<Integer, Bottle> get_bottleHashMap() {
        return bottleHashMap;
    }
    
    public HashMap<Integer, Food> get_foodHashMap() {
        return foodHashMap;
    }
    
    public BackPack get_backpack() {
        return advBackPack;
    }
    
    public void change_advLevel(int foodEnergy) {
        advLevel += foodEnergy;
    }
    
    public void change_advHitPoint(int number) {
        advHitPoint += number;
    }
    
    public void hire_adventurer(int advId) {
        Adventurer adventurer = FullMap.getAdvMap().get(advId);
        commodityHashMap.put(advId, adventurer);
    }
    
    public long getMaxPrice() {
        long max = 0;
        for (Commodity commodity : commodityHashMap.values()) {
            if (commodity.getPrice() > max) {
                max = commodity.getPrice();
            }
        }
        return max;
    }
    
    public HashMap<Integer, Commodity> getCommodityHashMap() {
        return commodityHashMap;
    }
    
    public void liquidate(int discrepancy) {
        for (Commodity commodity : commodityHashMap.values()) {
            if (commodity instanceof Adventurer) {
                long value = ((Adventurer) commodity).getMoney();
                long price = discrepancy * 10000L;
                if (value >= price) {
                    changeMoney(price);
                    ((Adventurer) commodity).changeMoney(-price);
                } else {
                    changeMoney(value);
                    ((Adventurer) commodity).changeMoney(-value);
                }
            }
        }
    }
    
    public Long emptyBackPack() {
        long sum = 0L;
        if (!get_backpack().get_bottleHashMap().isEmpty()) {
            ArrayList<Integer> delete = new ArrayList<>();
            for (TreeMap<Integer, Bottle> map : get_backpack().get_bottleHashMap().values()) {
                for (Bottle bot : map.values()) {
                    int botId = bot.getBot_id();
                    String botName = get_bottleHashMap().get(botId).getBot_name();
                    int botCap = get_bottleHashMap().get(botId).getOriginalCapacity();
                    long botPrice = get_bottleHashMap().get(botId).getPrice();
                    Bottle bottle = new Bottle(botId, botName, botCap, botPrice);
                    sum += botPrice;
                    Store.getBottlePurchaseRecord().add(bottle);
                    delete.add(botId);
                }
            }
            for (int i : delete) {
                delete_bottle(i);
            }
        }
        if (!get_backpack().get_equipmentHashMap().isEmpty()) {
            ArrayList<Integer> delete = new ArrayList<>();
            for (TreeMap<Integer, Equipment> map : get_backpack().get_equipmentHashMap().values()) {
                for (Equipment equip : map.values()) {
                    int equipId = equip.getEqu_id();
                    String equipName = get_equipmentHashMap().get(equipId).getEqu_name();
                    int equipStars = get_equipmentHashMap().get(equipId).getEqu_star();
                    long equipPrice = get_equipmentHashMap().get(equipId).getPrice();
                    Equipment equipment = new Equipment(equipId, equipName, equipStars, equipPrice);
                    sum += equipPrice;
                    Store.getEquipPurchaseRecord().add(equipment);
                    delete.add(equipId);
                }
            }
            for (int i : delete) {
                delete_equipment(i);
            }
        }
        if (!get_backpack().get_foodHashMap().isEmpty()) {
            ArrayList<Integer> delete = new ArrayList<>();
            for (TreeMap<Integer, Food> foodTreeMap : get_backpack().get_foodHashMap().values()) {
                for (Food f : foodTreeMap.values()) {
                    int foodId = f.getFood_Id();
                    String foodName = get_foodHashMap().get(foodId).getFood_name();
                    int foodEnergy = get_foodHashMap().get(foodId).getFood_Energy();
                    long foodPrice = get_foodHashMap().get(foodId).getPrice();
                    Food food = new Food(foodId, foodName, foodEnergy, foodPrice);
                    sum += foodPrice;
                    Store.getFoodPurchaseRecord().add(food);
                    delete.add(foodId);
                }
            }
            for (int i : delete) {
                delete_food(i);
            }
        }
        this.money += sum;
        return sum;
    }
}