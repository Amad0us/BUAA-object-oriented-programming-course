import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CaseSelection {
    public CaseSelection() {
    
    }
    
    public static void dealWithScanner(String[] strings, Scanner scanner) {
        switch (strings[0]) {
            case "1": Case1(strings);
                break;
            case "2": Case2(strings);
                break;
            case "3": Case3(strings);
                break;
            case "4": Case4(strings);
                break;
            case "5": Case5(strings);
                break;
            case "6": Case6(strings);
                break;
            case "7": Case7(strings);
                break;
            case "8": Case8(strings);
                break;
            case "9": Case9(strings);
                break;
            case "10": Case10(strings);
                break;
            case "11": Case11(strings);
                break;
            case "12": Case12(strings);
                break;
            case "13": Case13(strings);
                break;
            case "14": Case14(strings, scanner);
                break;
            case "15": Case15(strings);
                break;
            case "16": Case16(strings);
                break;
            case "17": Case17(strings);
                break;
            case "18": Case18(strings);
                break;
            case "19": Case19(strings);
                break;
            case "20": Case20(strings);
                break;
            case "21": Case21(strings);
                break;
            case "22": Case22(strings);
                break;
            case "23": Case23(strings);
                break;
            default:
                break;
        }
    }
    
    public static void Case1(String[] strings) {
        int id = Integer.parseInt(strings[1]);
        FullMap.add_adventurer(id, strings[2]);
    }
    
    public static void Case2(String[] strings) {
        int advId = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(advId);
        int botId = Integer.parseInt(strings[2]);
        String name = strings[3];
        int capacity = Integer.parseInt(strings[4]);
        long price = Integer.parseInt(strings[5]);
        String type = strings[6];
        if (type.equals("RegularBottle")) {
            RegularBottle bottle = new RegularBottle(botId, name, capacity, price);
            person.add_bottle(botId, bottle);
        } else if (type.equals("ReinforcedBottle")) {
            ReinforcedBottle bottle = new ReinforcedBottle(botId, name, capacity, price);
            double others = Double.parseDouble(strings[7]);
            bottle.putRatio(others);
            person.add_bottle(botId, bottle);
        } else if (type.equals("RecoverBottle")) {
            RecoverBottle bottle = new RecoverBottle(botId, name, capacity, price);
            double others = Double.parseDouble(strings[7]);
            bottle.putRatio(others);
            person.add_bottle(botId, bottle);
        }
    }
    
    public static void Case3(String[] strings) {
        int id2 = Integer.parseInt(strings[2]);
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int tempNum;
        tempNum = person.get_bottle_number() - 1;
        String tempString;
        tempString = person.get_bottleHashMap().get(id2).getBot_name();
        int botId = person.get_bottleHashMap().get(id2).getBot_id();
        String botName = person.get_bottleHashMap().get(id2).getBot_name();
        int botCap = person.get_bottleHashMap().get(id2).getOriginalCapacity();
        long botPrice = person.get_bottleHashMap().get(id2).getPrice();
        Bottle bottle = new Bottle(botId, botName, botCap, botPrice);
        Store.getBottlePurchaseRecord().add(bottle);
        person.changeMoney(botPrice);
        person.delete_bottle(id2);
        System.out.print(tempNum + " ");
        System.out.print(tempString + "\n");
    }
    
    public static void Case4(String[] strings) {
        int advId = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(advId);
        int equId = Integer.parseInt(strings[2]);
        String name = strings[3];
        int star = Integer.parseInt(strings[4]);
        int price = Integer.parseInt(strings[5]);
        String type = strings[6];
        if (type.equals("RegularEquipment")) {
            RegularEquipment equipment = new RegularEquipment(equId, name, star, price);
            person.add_equipment(equId, equipment);
        } else if (type.equals("CritEquipment")) {
            CritEquipment equipment = new CritEquipment(equId, name, star, price);
            int others = Integer.parseInt(strings[7]);
            equipment.putCritical(others);
            person.add_equipment(equId, equipment);
        } else if (type.equals("EpicEquipment")) {
            EpicEquipment equipment = new EpicEquipment(equId, name, star, price);
            double others = Double.parseDouble(strings[7]);
            equipment.putRatio(others);
            person.add_equipment(equId, equipment);
        }
    }
    
    public static void Case5(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int tempNum;
        tempNum = person.get_equipment_number() - 1;
        int id2 = Integer.parseInt(strings[2]);
        String tempString;
        tempString = person.get_equipmentHashMap().get(id2).getEqu_name();
        int equipId = person.get_equipmentHashMap().get(id2).getEqu_id();
        String equipName = person.get_equipmentHashMap().get(id2).getEqu_name();
        int equipStars = person.get_equipmentHashMap().get(id2).getEqu_star();
        long equipPrice = person.get_equipmentHashMap().get(id2).getPrice();
        Equipment equipment = new Equipment(equipId, equipName, equipStars, equipPrice);
        Store.getEquipPurchaseRecord().add(equipment);
        person.changeMoney(equipPrice);
        person.delete_equipment(id2);
        System.out.print(tempNum + " ");
        System.out.print(tempString + "\n");
    }
    
    public static void Case6(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int id2 = Integer.parseInt(strings[2]);
        person.get_equipmentHashMap().get(id2).star_rise();
        System.out.print(person.get_equipmentHashMap().get(id2).getEqu_name());
        System.out.print(" ");
        System.out.print(person.get_equipmentHashMap().get(id2).getEqu_star());
        System.out.print("\n");
    }
    
    public static void Case7(String[] strings) {
        int advId = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(advId);
        int foodId = Integer.parseInt(strings[2]);
        String name = strings[3];
        int energy = Integer.parseInt(strings[4]);
        int price = Integer.parseInt(strings[5]);
        Food food = new Food(foodId, name, energy, price);
        person.add_food(foodId, food);
    }
    
    public static void Case8(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int id2 = Integer.parseInt(strings[2]);
        int tempNum;
        tempNum = person.get_food_number() - 1;
        String tempString;
        tempString = person.get_foodHashMap().get(id2).getFood_name();
        int foodId = person.get_foodHashMap().get(id2).getFood_Id();
        String foodName = person.get_foodHashMap().get(id2).getFood_name();
        int foodEnergy = person.get_foodHashMap().get(id2).getFood_Energy();
        long foodPrice = person.get_foodHashMap().get(id2).getPrice();
        Food food = new Food(foodId, foodName, foodEnergy, foodPrice);
        Store.getFoodPurchaseRecord().add(food);
        person.changeMoney(foodPrice);
        person.delete_food(id2);
        System.out.print(tempNum + " ");
        System.out.print(tempString + "\n");
    }
    
    public static void Case9(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int id2 = Integer.parseInt(strings[2]);
        String equName = person.get_equipmentHashMap().get(id2).getEqu_name();
        int equStar = person.get_equipmentHashMap().get(id2).getEqu_star();
        long price = person.get_equipmentHashMap().get(id2).getPrice();
        person.get_backpack().carry_equipment(id2, equName, equStar, price);
    }
    
    public static void Case10(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int id2 = Integer.parseInt(strings[2]);
        String botName = person.get_bottleHashMap().get(id2).getBot_name();
        int botCapacity = person.get_bottleHashMap().get(id2).getBot_capacity();
        double temp = person.get_advLevel() / 5.0;
        temp = Math.floor(temp) + 1;
        int max = (int) temp;
        long price = person.get_bottleHashMap().get(id2).getPrice();
        person.get_backpack().carry_bottle(id2, botName, botCapacity, max, price);
    }
    
    public static void Case11(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int id2 = Integer.parseInt(strings[2]);
        String foodName = person.get_foodHashMap().get(id2).getFood_name();
        int foodEnergy = person.get_foodHashMap().get(id2).getFood_Energy();
        long price = person.get_foodHashMap().get(id2).getPrice();
        person.get_backpack().carry_food(id2, foodName, foodEnergy, price);
    }
    
    public static void Case12(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int botCapacity = person.get_backpack().use_bottle(strings[2]);
        int tempNum = person.get_backpack().get_bottleLeast(strings[2]);
        Bottle bottle = person.get_bottleHashMap().get(tempNum);
        int hitPoint = person.get_advHitPoint();
        if (botCapacity == -1) {
            System.out.print("fail to use " + strings[2] + "\n");
        } else if (botCapacity == 0 && bottle.getIsempty() == 0) {
            System.out.print(tempNum + " " + person.get_advHitPoint() + "\n");
            person.delete_bottle(tempNum);
        } else {
            int heal = ToolFunction.getHeal(bottle, botCapacity, hitPoint);
            person.change_advHitPoint(heal);
            person.get_bottleHashMap().get(tempNum).change_bottle(botCapacity);
            bottle.changeIsEmpty();
            System.out.print(tempNum + " " + person.get_advHitPoint() + "\n");
        }
    }
    
    public static void Case13(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        int foodEnergy = person.get_backpack().use_food(strings[2]);
        int tempNum = person.get_backpack().get_foodLeast(strings[2]);
        if (foodEnergy == -1) {
            System.out.print("fail to eat " + strings[2] + "\n");
        } else {
            person.change_advLevel(foodEnergy);
            person.delete_food(tempNum);
            System.out.print(tempNum + " " + person.get_advLevel() + "\n");
        }
    }
    
    public static void Case14(String[] strings, Scanner scanner) {
        int personNumber = Integer.parseInt(strings[1]);
        int listNumber = Integer.parseInt(strings[2]);
        String tempString;
        ArrayList<String> participants = new ArrayList<>();
        HashMap<String, Integer> initialHitPoint = new HashMap<>();
        for (int p = 3; p < 3 + personNumber; p++) {
            tempString = strings[p];
            participants.add(tempString);
            int id = FullMap.getNameMap().get(tempString);
            int hitPoint = FullMap.getAdvMap().get(id).get_advHitPoint();
            initialHitPoint.put(tempString, hitPoint);
        }
        System.out.println("Enter Fight Mode");
        for (int l = 0; l < listNumber; l++) {
            tempString = scanner.nextLine();
            tempString = tempString.trim();
            ToolFunction.SumMatch(tempString, participants);
        }
        for (int p = 3; p < 3 + personNumber; p++) {
            String name = strings[p];
            int id = FullMap.getNameMap().get(name);
            Adventurer person = FullMap.getAdvMap().get(id);
            int hitPointForward = initialHitPoint.get(name);
            double temp = hitPointForward * (1.0) / 2;
            int halfHitPoint = (int) Math.floor(temp);
            int hitPointAfterward = person.get_advHitPoint();
            if (hitPointAfterward <= halfHitPoint) {
                person.liquidate(hitPointForward - hitPointAfterward);
            }
        }
    }
    
    public static void Case15(String[] strings) {
        String time = strings[1];
        if (!FullMap.getBattleLog().get_log().containsKey(time)) {
            System.out.println("No Matched Log");
            return;
        }
        for (String tempList : FullMap.getBattleLog().get_log().get(time)) {
            System.out.println(tempList);
        }
    }
    
    public static void Case16(String[] strings) {
        int id = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id);
        if (person.get_attacks().isEmpty()) {
            System.out.println("No Matched Log");
            return;
        }
        for (String tempString : person.get_attacks()) {
            System.out.println(tempString);
        }
    }
    
    public static void Case17(String[] strings) {
        int id = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id);
        if (person.get_attacked().isEmpty()) {
            System.out.println("No Matched Log");
            return;
        }
        for (String tempString : person.get_attacked()) {
            System.out.println(tempString);
        }
    }
    
    public static void Case18(String[] strings) {
        int id1 = Integer.parseInt(strings[1]);
        int id2 = Integer.parseInt(strings[2]);
        Adventurer person = FullMap.getAdvMap().get(id1);
        person.hire_adventurer(id2);
    }
    
    public static void Case19(String[] strings) {
        int id = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id);
        int num = person.getCommoditySize();
        long price = person.getPrice();
        price -= person.getMoney();
        System.out.println(num + " " + price);
    }
    
    public static void Case20(String[] strings) {
        long num = 0;
        int id = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id);
        if (person.getCommoditySize() == 0) {
            System.out.println("0");
        } else {
            num = person.getMaxPrice();
            System.out.println(num);
        }
    }
    
    public static void Case21(String[] strings) {
        int advId = Integer.parseInt(strings[1]);
        int comId = Integer.parseInt(strings[2]);
        Adventurer person = FullMap.getAdvMap().get(advId);
        Commodity commodity = person.getCommodityHashMap().get(comId);
        if (commodity instanceof Adventurer) {
            System.out.println("Commodity whose id is " + comId + " belongs to Adventurer");
        } else if (commodity instanceof Food) {
            System.out.println("Commodity whose id is " + comId + " belongs to Food");
        } else if (commodity instanceof RegularBottle) {
            System.out.println("Commodity whose id is " + comId + " belongs to RegularBottle");
        } else if (commodity instanceof ReinforcedBottle) {
            System.out.println("Commodity whose id is " + comId + " belongs to ReinforcedBottle");
        } else if (commodity instanceof RecoverBottle) {
            System.out.println("Commodity whose id is " + comId + " belongs to RecoverBottle");
        } else if (commodity instanceof RegularEquipment) {
            System.out.println("Commodity whose id is " + comId + " belongs to RegularEquipment");
        } else if (commodity instanceof CritEquipment) {
            System.out.println("Commodity whose id is " + comId + " belongs to CritEquipment");
        } else if (commodity instanceof EpicEquipment) {
            System.out.println("Commodity whose id is " + comId + " belongs to EpicEquipment");
        }
    }
    
    public static void Case22(String[] strings) {
        int id = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(id);
        String name = person.getAdvName();
        Long money = person.emptyBackPack();
        System.out.println(name + " emptied the backpack " + money);
    }
    
    public static void Case23(String[] strings) {
        int advId = Integer.parseInt(strings[1]);
        Adventurer person = FullMap.getAdvMap().get(advId);
        long money = person.getMoney();
        int itemId = Integer.parseInt(strings[2]);
        String itemName = strings[3];
        String itemType = strings[4];
        int tempNum = 0;
        long price = 0;
        int mark = 0;
        if (itemType.equals("RegularBottle")) {
            tempNum = Store.getAverage(1);
            price = Store.getPriceAverage(1);
            mark = ToolFunction.judge1(money, price, itemId, itemName, tempNum, person);
        } else if (itemType.equals("RecoverBottle")) {
            tempNum = Store.getAverage(1);
            price = Store.getPriceAverage(1);
            double others = Double.parseDouble(strings[5]);
            mark = ToolFunction.judge4(money, price, itemId, itemName, tempNum, person, others);
        } else if (itemType.equals("ReinforcedBottle")) {
            tempNum = Store.getAverage(1);
            price = Store.getPriceAverage(1);
            double others = Double.parseDouble(strings[5]);
            mark = ToolFunction.judge5(money, price, itemId, itemName, tempNum, person, others);
        } else if (itemType.equals("RegularEquipment")) {
            tempNum = Store.getAverage(2);
            price = Store.getPriceAverage(2);
            mark = ToolFunction.judge2(money, price, itemId, itemName, tempNum, person);
        } else if (itemType.equals("CritEquipment")) {
            tempNum = Store.getAverage(2);
            price = Store.getPriceAverage(2);
            int others = Integer.parseInt(strings[5]);
            mark = ToolFunction.judge6(money, price, itemId, itemName, tempNum, person, others);
        } else if (itemType.equals("EpicEquipment")) {
            tempNum = Store.getAverage(2);
            price = Store.getPriceAverage(2);
            double others = Double.parseDouble(strings[5]);
            mark = ToolFunction.judge7(money, price, itemId, itemName, tempNum, person, others);
        } else if (itemType.equals("Food")) {
            tempNum = Store.getAverage(3);
            price = Store.getPriceAverage(3);
            mark = ToolFunction.judge3(money, price, itemId, itemName, tempNum, person);
        }
        if (mark == 1) {
            person.changeMoney(-price);
            System.out.println("successfully buy " + itemName + " for " + price);
        } else {
            System.out.println("failed to buy " + itemName + " for " + price);
        }
    }
}
