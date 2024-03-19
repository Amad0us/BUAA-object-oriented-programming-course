import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdventurerTest {
    Adventurer adventurer = new Adventurer(1020, "op");
    
    @Test
    public void add_bottle() {
        Bottle bottle = new Bottle(2200, "Pen", 1000, 1);
        adventurer.add_bottle(2200, bottle);
        assert (adventurer.get_bottleHashMap() != null);
        assert (adventurer.get_bottleHashMap().get(2200).getBot_id() == 2200);
        assert (adventurer.get_bottleHashMap().get(2200).getBot_name().equals("Pen"));
        assert (adventurer.get_bottleHashMap().get(2200).getBot_capacity() == 1000);
        assert (adventurer.get_bottle_number() != 0);
        adventurer.delete_bottle(2200);
    }
    
    @Test
    public void add_equipment() {
        Equipment equipment = new Equipment(2800, "Pencil", 5, 1);
        adventurer.add_equipment(2800, equipment);
        adventurer.get_equipmentHashMap().get(2800).star_rise();
        assert (adventurer.get_equipmentHashMap().get(2800).getEqu_star() == 6);
        assert (adventurer.get_equipmentHashMap() != null);
        assert (adventurer.get_equipmentHashMap().get(2800).getEqu_id() == 2800);
        assert (adventurer.get_equipmentHashMap().get(2800).getEqu_name().equals("Pencil"));
        assert (adventurer.get_equipment_number() == 1);
        adventurer.delete_equipment(2800);
    }
    
    @Test
    public void add_food() {
        Food food = new Food(1000, "now", 10, 1);
        adventurer.add_food(1000, food);
        int op = adventurer.get_advHitPoint();
        assert (op == 500);
        op = adventurer.get_advLevel();
        assert (op == 1);
        adventurer.change_advLevel(1);
        op = adventurer.get_advLevel();
        assert (op == 2);
        adventurer.change_advHitPoint(10);
        op = adventurer.get_advHitPoint();
        assert (op == 510);
        int temp = adventurer.get_foodHashMap().get(1000).getFood_Energy();
        assert (temp == 10);
        temp = adventurer.get_foodHashMap().get(1000).getFood_Id();
        assert (temp == 1000);
        String t = adventurer.get_foodHashMap().get(1000).getFood_name();
        assert (t.equals("now"));
        assert (adventurer.get_food_number() == 1);
        adventurer.delete_food(1000);
        op = adventurer.get_food_number();
        assert (op == 0);
    }
    
    @Test
    public void get_backpack() {
        BackPack backPack = adventurer.get_backpack();
        int op = backPack.get_food_number();
        assert (op == 0);
        op = backPack.get_bottle_number();
        assert (op == 0);
        op = backPack.get_equipment_number();
        assert (op == 0);
        backPack.use_bottle("as");
        backPack.get_bottleLeast("as");
        backPack.carry_bottle(1, "as", 1, 1, 1);
        backPack.carry_bottle(2, "as", 1, 1, 1);
        backPack.use_bottle("as");
        backPack.get_bottleLeast("as");
        backPack.use_bottle("as");
        backPack.unload_bottle(1, "as");
        backPack.use_food("as");
        backPack.get_foodLeast("as");
        backPack.carry_food(1, "as", 1, 1);
        backPack.use_food("as");
        backPack.get_foodLeast("as");
        backPack.unload_bottle(1, "as");
        backPack.carry_equipment(1, "as", 1, 1);
        backPack.unload_equipment("as", 1);
    }
    
    String[] arr = new String[]{"10000", "1000", "100", "10", "1", "1", "RecoverBottle", "1"};
    String[] arr1 = new String[]{"10000", "1000", "100", "1", "1"};
    Scanner scanner = new Scanner(System.in);
    
    @Test
    public void testMainTest() {
        FullMap.add_adventurer(1000, "100");
        FullMap.add_adventurer(11, "X!Tx10UX");
        FullMap.add_adventurer(111, "ReD?Z?&");
        FullMap.add_adventurer(1111, "ReD?Z?&");
        String tempString = "sum";
        String tempString1 = "2000/02-X!Tx10UX@ReD?Z?&-Pc&XR=";
        String tempString2 = "2000/04-X!Tx10UX@#-Trans/=+";
        String tempString3 = "2000/03-X!Tx10UX-=W7^D";
        ArrayList<String> participants = new ArrayList<>();
        participants.add("X!Tx10UX");
        participants.add("ReD?Z?&");
        participants.add("Pc&XR=");
        CaseSelection.Case1(arr);
        CaseSelection.Case2(arr);
        CaseSelection.Case3(arr);
        CaseSelection.Case2(arr);
        CaseSelection.Case4(arr);
        CaseSelection.Case5(arr);
        CaseSelection.Case4(arr);
        CaseSelection.Case6(arr1);
        CaseSelection.Case7(arr);
        CaseSelection.Case8(arr);
        CaseSelection.Case7(arr);
        CaseSelection.Case9(arr1);
        CaseSelection.Case10(arr1);
        CaseSelection.Case11(arr1);
        CaseSelection.Case12(arr);
        CaseSelection.Case13(arr);
        CaseSelection.Case15(arr);
        CaseSelection.Case16(arr);
        CaseSelection.Case17(arr);
        String strings1[] = new String[]{"1", "1", "1"};
        CaseSelection.dealWithScanner(strings1, scanner);
        String strings2[] = new String[]{"2", "1", "1", "1", "1"};
        CaseSelection.dealWithScanner(strings2, scanner);
        String strings3[] = new String[]{"3", "1", "1"};
        CaseSelection.dealWithScanner(strings3, scanner);
        String strings4[] = new String[]{"4", "1", "1", "1", "1"};
        CaseSelection.dealWithScanner(strings4, scanner);
        String strings5[] = new String[]{"5", "1", "1"};
        CaseSelection.dealWithScanner(strings5, scanner);
        String strings6[] = new String[]{"6", "1", "1"};
        CaseSelection.dealWithScanner(strings4, scanner);
        CaseSelection.dealWithScanner(strings6, scanner);
        String strings7[] = new String[]{"7", "1", "1", "1", "1"};
        CaseSelection.dealWithScanner(strings7, scanner);
        String strings8[] = new String[]{"8", "1", "1"};
        CaseSelection.dealWithScanner(strings8, scanner);
        String strings9[] = new String[]{"9", "1", "1"};
        CaseSelection.dealWithScanner(strings9, scanner);
        String strings10[] = new String[]{"10", "1", "1"};
        CaseSelection.dealWithScanner(strings2, scanner);
        CaseSelection.dealWithScanner(strings10, scanner);
        String strings11[] = new String[]{"11", "1", "1"};
        CaseSelection.dealWithScanner(strings7, scanner);
        CaseSelection.dealWithScanner(strings11, scanner);
        String strings12[] = new String[]{"12", "1", "1"};
        CaseSelection.dealWithScanner(strings12, scanner);
        String strings13[] = new String[]{"13", "1", "1"};
        CaseSelection.dealWithScanner(strings13, scanner);
        String strings14[] = new String[]{"14", "1", "0", "1"};
        CaseSelection.dealWithScanner(strings14, scanner);
        String strings15[] = new String[]{"15", "1"};
        CaseSelection.dealWithScanner(strings15, scanner);
        String strings16[] = new String[]{"16", "1"};
        CaseSelection.dealWithScanner(strings16, scanner);
        String strings17[] = new String[]{"17", "1"};
        CaseSelection.dealWithScanner(strings17, scanner);
        ToolFunction.SumMatch(tempString, participants);
        String pattern01 = "(\\d{4})/(\\d{2})-([^@#-]*)-([^@#-]*)";
        String pattern02 = "(\\d{4})/(\\d{2})-([^@#-]*)@([^@#-]*)-([^@#-]*)";
        String pattern03 = "(\\d{4})/(\\d{2})-([^@#-]*)@#-([^@#-]*)";
        Pattern pattern1 = Pattern.compile(pattern01);
        Pattern pattern2 = Pattern.compile(pattern02);
        Pattern pattern3 = Pattern.compile(pattern03);
        Matcher matcher1;
        Matcher matcher2;
        Matcher matcher3;
        matcher1 = pattern1.matcher(tempString3);
        matcher2 = pattern2.matcher(tempString1);
        matcher3 = pattern3.matcher(tempString2);
        if (matcher1.find()) {
            ToolFunction.Match1(matcher1, participants);
        }
        if (matcher2.find()) {
            ToolFunction.Match2(matcher2, participants);
        }
        if (matcher3.find()) {
            ToolFunction.Match3(matcher3, participants);
        }
        BattleLog battleLog = new BattleLog();
        battleLog.addList("1", "1");
        battleLog.addList("1", "1");
        Bottle bottle = new Bottle(1, "1", 1, 1);
        bottle.change_bottle(1);
        BackPack backPack = new BackPack();
        backPack.carry_bottle(1, "1", -1, 10000, 1);
        backPack.carry_bottle(1, "1", -1, 10000, 1);
        backPack.use_bottle("1");
        backPack.carry_food(1, "1", 1, 1);
        backPack.carry_food(1, "1", 1, 1);
        backPack.carry_food(2, "1", 1, 1);
        backPack.unload_food(2, "1");
    }
}