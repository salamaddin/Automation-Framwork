package Lib.Files;

import java.util.HashMap;

public class TestData {
	Script script;
	FilloXL xl;
	String filePath;
	protected static HashMap<String, User> env;

	public TestData(Script script, HashMap<String, User> env) throws Exception
	{
		this.script   = script;
		this.env   = env;
		this.script   = script;
		xl = new FilloXL();
		loadTestData();
		display();
	}
	
	public TestData setENVs(HashMap<String,User> env)
	{
		this.env = env;
		return this;
	}
	
	public void loadTestData() throws Exception {
		try {
			filePath = "C:/Users/arka.sarkar/Desktop/temp/";
			String testDataFilePath = filePath + script.getTestID()+".xlsx";
			xl.getRecords(testDataFilePath, "TestData");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Test data is not found");
			e.printStackTrace();
		}
	}
	
	public void display() throws Exception {
		while(xl.next()) {
		System.out.println("print "+xl.get("Severity"));
		}
	}
	
	public String data(String ColumnName) throws Exception {
		if(ColumnName.equalsIgnoreCase("UserName")) {
			
			String userRole = xl.get(ColumnName);
			User users = env.get(userRole);
			
		}
		return xl.get(ColumnName);
		
	}

}