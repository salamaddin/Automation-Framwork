package Lib.Files;

import java.util.HashMap;


public class Base {
    protected HashMap<String, String> testDataMap;
    public TestData testData;
    public Web web;
    public String name;

    public Base() {
    }
    

    public void setTestData(TestData testData) {
    	 this.testData = testData;
    }

    public String getData(String colName) {
        return testData.get(colName);
    }


	public void setWeb() {
		name = "Sk";
	}
}

