package online.flowerinsnow.greatscrollabletooltips.util;

import java.util.HashMap;

/**
 * 将键视为ID
 */
public class IDHashMap extends HashMap<String, String> {
    @Override
    public String get(Object key) {
        return super.getOrDefault(key, (String) key);
    }

    // 原版构造方法
    public IDHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public IDHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public IDHashMap() {
        super();
    }
}
