import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * TimeBasedKVStore_981
 */
public class TimeBasedKVStore_981 {
    class TimeMap {
        Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<String, TreeMap<Integer, String>>();
        }

        public void set(String key, String value, int timestamp) {
            map.putIfAbsent(key, new TreeMap<Integer, String>());
            TreeMap<Integer, String> valueMap = map.get(key);
            valueMap.put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> valueMap = map.get(key);
            if (valueMap == null)
                return "";
            // Integer k = valueMap.floorKey(timestamp);
            Map.Entry<Integer, String> e = valueMap.floorEntry(timestamp);
            if (e == null)
                return "";
            // return valueMap.get(k);
            return e.getValue();
        }
    }
}