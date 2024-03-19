import java.util.HashMap;
import java.util.Scanner;

public class FullMap {
    private static HashMap<Integer, Adventurer> advMap = new HashMap<>();
    private static HashMap<String, Integer> nameMap = new HashMap<>();
    private static BattleLog battleLog = new BattleLog();
    private Scanner scanner;
    
    public FullMap() {
    
    }
    
    public static HashMap<Integer, Adventurer> getAdvMap() {
        return advMap;
    }
    
    public static HashMap<String, Integer> getNameMap() {
        return nameMap;
    }
    
    public static BattleLog getBattleLog() {
        return battleLog;
    }
    
    public static void add_adventurer(int advId, String advName) {
        Adventurer adventurer = new Adventurer(advId, advName);
        advMap.put(advId, adventurer);
        nameMap.put(advName, advId);
    }
    
}
