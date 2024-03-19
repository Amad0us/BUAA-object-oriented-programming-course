import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolFunction {
    
    public static int getHeal(Bottle bottle, int botCapacity, int hitPoint) {
        int heal = 0;
        if (bottle instanceof RegularBottle) {
            heal = botCapacity;
        } else if (bottle instanceof ReinforcedBottle) {
            double ratio = ((ReinforcedBottle) bottle).getRatio();
            double temp = (1 + ratio) * botCapacity;
            heal = (int) Math.floor(temp);
        } else if (bottle instanceof RecoverBottle) {
            double ratio = ((RecoverBottle) bottle).getRatio();
            double temp = (ratio) * hitPoint;
            heal = (int) Math.floor(temp);
        }
        return heal;
    }
    
    public static int getDamage(int id, int star, Adventurer p1, Adventurer p2, Equipment equip) {
        int damage = 0;
        int level;
        level = p1.get_advLevel();
        if (p1.get_equipmentHashMap().get(id) instanceof RegularEquipment) {
            damage = level * star;
        } else if (p1.get_equipmentHashMap().get(id) instanceof CritEquipment) {
            int last = ((CritEquipment) equip).getCritical();
            damage = level * star + last;
        } else if (p1.get_equipmentHashMap().get(id) instanceof EpicEquipment) {
            double ratio = ((EpicEquipment) equip).getRatio();
            double temp = Math.floor(p2.get_advHitPoint() * ratio);
            damage = (int) temp;
        }
        p2.change_advHitPoint((-1) * damage);
        return damage;
    }
    
    public static void SumMatch(String log, ArrayList<String> participants) {
        String pattern01 = "(\\d{4})/(\\d{2})-([^@#-]*)-([^@#-]*)";
        String pattern02 = "(\\d{4})/(\\d{2})-([^@#-]*)@([^@#-]*)-([^@#-]*)";
        String pattern03 = "(\\d{4})/(\\d{2})-([^@#-]*)@#-([^@#-]*)";
        Pattern pattern1 = Pattern.compile(pattern01);
        Pattern pattern2 = Pattern.compile(pattern02);
        Pattern pattern3 = Pattern.compile(pattern03);
        Matcher matcher1;
        Matcher matcher2;
        Matcher matcher3;
        matcher1 = pattern1.matcher(log);
        matcher2 = pattern2.matcher(log);
        matcher3 = pattern3.matcher(log);
        if (matcher1.find()) {
            Match1(matcher1, participants);
        } else if (matcher2.find()) {
            Match2(matcher2, participants);
        } else if (matcher3.find()) {
            Match3(matcher3, participants);
        } else {
            System.out.println("Fight log error");
        }
    }
    
    public static void Match1(Matcher matcher1, ArrayList<String> participants) {
        //战斗中喝药
        String tempList;
        String time = matcher1.group(1) + "/" + matcher1.group(2);
        String player = matcher1.group(3);
        String bottle0 = matcher1.group(4);
        int botCapacity;
        int tempNum;
        int id = FullMap.getNameMap().get(player);
        Adventurer person = FullMap.getAdvMap().get(id);
        boolean tmp = participants.contains(player);
        if (!tmp || !person.get_backpack().get_bottleHashMap().containsKey(bottle0)) {
            System.out.println("Fight log error");
        } else {
            botCapacity = person.get_backpack().use_bottle(bottle0);
            tempNum = person.get_backpack().get_bottleLeast(bottle0);
            Bottle bottle = person.get_bottleHashMap().get(tempNum);
            if (botCapacity == -1) {
                System.out.println("Fight log error");
            } else if (botCapacity == 0 && bottle.getIsempty() == 0) {
                System.out.println(tempNum + " " + person.get_advHitPoint());
                person.delete_bottle(tempNum);
                tempList = time + " " + player + " " + "used" + " " + bottle0;
                FullMap.getBattleLog().addList(time, tempList);
            } else {
                int heal = 0;
                int hitPoint = person.get_advHitPoint();
                heal = getHeal(bottle, botCapacity, hitPoint);
                person.change_advHitPoint(heal);
                person.get_bottleHashMap().get(tempNum).change_bottle(botCapacity);
                System.out.println(tempNum + " " + person.get_advHitPoint());
                tempList = time + " " + player + " " + "used" + " " + bottle0;
                FullMap.getBattleLog().addList(time, tempList);
                bottle.changeIsEmpty();
            }
        }
    }
    
    public static void Match2(Matcher matcher2, ArrayList<String> participants) {
        //战斗中1v1
        String tempList;
        String time = matcher2.group(1) + "/" + matcher2.group(2);
        String player1 = matcher2.group(3);
        String player2 = matcher2.group(4);
        String equipName = matcher2.group(5);
        int id1 = FullMap.getNameMap().get(player1);
        int id2 = FullMap.getNameMap().get(player2);
        int damage = 0;
        int equipId;
        int star;
        int level;
        Adventurer person1 = FullMap.getAdvMap().get(id1);
        Adventurer person2 = FullMap.getAdvMap().get(id2);
        boolean tmp = participants.contains(player1) && participants.contains(player2);
        if (!tmp || !person1.get_backpack().get_equipmentHashMap().containsKey(equipName)) {
            System.out.println("Fight log error");
        } else {
            
            equipId = person1.get_backpack().get_equipmentHashMap().get(equipName).firstKey();
            Equipment equipment = person1.get_equipmentHashMap().get(equipId);
            star = person1.get_equipmentHashMap().get(equipId).getEqu_star();
            damage = getDamage(equipId, star, person1, person2, equipment);
            System.out.println(id2 + " " + person2.get_advHitPoint());
            String t;
            t = time + " " + player1 + " " + "attacked";
            tempList = t + " " + player2 + " " + "with" + " " + equipName;
            FullMap.getBattleLog().addList(time, tempList);
            person1.get_attacks().add(tempList);
            person2.get_attacked().add(tempList);
        }
    }
    
    public static void Match3(Matcher matcher3, ArrayList<String> participants) {
        //AOE
        String time = matcher3.group(1) + "/" + matcher3.group(2);
        String player = matcher3.group(3);
        String equipName = matcher3.group(4);
        int damage = 0;
        int equipId;
        int star;
        int id = FullMap.getNameMap().get(player);
        int tempId;
        int level;
        Adventurer person1 = FullMap.getAdvMap().get(id);
        Adventurer person2;
        boolean tmp = participants.contains(player);
        String tempList = time + " " + player + " " + "AOE-attacked with" + " " + equipName;
        if (!tmp || !person1.get_backpack().get_equipmentHashMap().containsKey(equipName)) {
            System.out.println("Fight log error");
        } else {
            equipId = person1.get_backpack().get_equipmentHashMap().get(equipName).firstKey();
            Equipment equipment = person1.get_equipmentHashMap().get(equipId);
            star = person1.get_equipmentHashMap().get(equipId).getEqu_star();
            FullMap.getBattleLog().addList(time, tempList);
            person1.get_attacks().add(tempList);
            for (String advName : participants) {
                if (advName.equals(player)) {
                    continue;
                } else {
                    tempId = FullMap.getNameMap().get(advName);
                    person2 = FullMap.getAdvMap().get(tempId);
                    damage = getDamage(equipId, star, person1, person2, equipment);
                    System.out.print(person2.get_advHitPoint() + " ");
                    person2.get_attacked().add(tempList);
                }
            }
            System.out.print("\n");
        }
    }
    
    public static int judge1(long money, long price, int id, String name, int num, Adventurer per) {
        if (money >= price) {
            RegularBottle bottle = new RegularBottle(id, name, num, price);
            per.add_bottle(id, bottle);
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int judge2(long money, long price, int id, String name, int num, Adventurer per) {
        if (money >= price) {
            RegularEquipment equipment = new RegularEquipment(id, name, num, price);
            per.add_equipment(id, equipment);
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int judge3(long money, long price, int id, String name, int num, Adventurer per) {
        if (money >= price) {
            Food food = new Food(id, name, num, price);
            per.add_food(id, food);
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int judge4(long a, long b, int id, String na, int n, Adventurer p, double ot) {
        if (a >= b) {
            RecoverBottle bottle = new RecoverBottle(id, na, n, b);
            bottle.putRatio(ot);
            p.add_bottle(id, bottle);
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int judge5(long a, long b, int id, String na, int n, Adventurer p, double ot) {
        if (a >= b) {
            ReinforcedBottle bottle = new ReinforcedBottle(id, na, n, b);
            bottle.putRatio(ot);
            p.add_bottle(id, bottle);
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int judge6(long a, long b, int id, String na, int n, Adventurer p, int ot) {
        if (a >= b) {
            CritEquipment equipment = new CritEquipment(id, na, n, b);
            equipment.putCritical(ot);
            p.add_equipment(id, equipment);
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int judge7(long a, long b, int id, String na, int n, Adventurer p, double ot) {
        if (a >= b) {
            EpicEquipment equipment = new EpicEquipment(id, na, n, b);
            equipment.putRatio(ot);
            p.add_equipment(id, equipment);
            return 1;
        } else {
            return 0;
        }
    }
}
