import java.util.ArrayList;
import java.util.HashMap;

public class BattleLog {
    private HashMap<String, ArrayList<String>> log = new HashMap<>();
    
    public HashMap<String, ArrayList<String>> get_log() {
        return log;
    }
    
    public void addList(String time, String tempList) {
        if (!log.containsKey(time)) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(tempList);
            log.put(time, tmp);
        } else {
            log.get(time).add(tempList);
        }
    }
    
    public BattleLog() {
    
    }
}
