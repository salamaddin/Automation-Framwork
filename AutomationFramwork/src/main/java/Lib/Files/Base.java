package Lib.Files;

import java.util.HashMap;

public class Base {
    protected HashMap<String, String> testDataMap;

    public Base() {
        testDataMap = new HashMap<>();
    }

    public void setTestData(String key, String value) {
        testDataMap.put(key, value);
    }

    public String getTestData(String key) {
        return testDataMap.get(key);
    }
}
