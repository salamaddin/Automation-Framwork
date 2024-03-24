package Lib.Files;

import java.util.HashMap;

import org.apache.poi.xdgf.util.Util;

import Utils.Web;


public class Base extends Util{
    protected HashMap<String, String> testDataMap;
    public TestData testData;
    public Web web;
    public String name;
    
    public Base() {
      //  this.web = new Web("chrome");
    }

    public void setTestData(TestData testData) {
    	 this.testData = testData;
    }

    public String getData(String colName) {
        return testData.get(colName);
    }

	public void setWeb(Web web) {
		this.web = web;	
	}

	
}

