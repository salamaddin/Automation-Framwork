package Lib.Files;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Scripts {
    private HashMap<Integer, Script> scripts;
    private int autoInc = 0;
    private int currScriptIndex = 1;
    private Script curr;
    private String cmpName = "";
    private int index;
    private FilloXL xl;
    private TestData testData;

    public Scripts() throws Exception {
        loadScripts();
    }

    public void loadScripts() throws Exception {
        xl = new FilloXL();
        scripts = new HashMap<Integer, Script>();
        xl.getRecords("C:/Users/arka.sarkar/Desktop/temp/MasterDriver.xlsx", "Scripts", " Run='Y'");
        while (xl.next()) {
            Script temp = new Script();
            temp.setTestID(xl.get("TestID"));
            autoInc++;
            scripts.put(autoInc, temp);
        }
    }

    public void setNext() {
        curr = scripts.get(currScriptIndex);
        currScriptIndex++;
    }

    public boolean hasNext() {
        return currScriptIndex < scripts.size();
    }

    public void runCurrentScript() throws Exception {
        xl = new FilloXL();
        setNext();
        testData = new TestData(curr, null);
        index = 2;
        System.out.println("Current Test ID: " + curr.getTestID());
        xl.getRecords("C:/Users/arka.sarkar/Desktop/temp/ACT_MasterDriver.xlsx", "TAComponents",
                " TestID ='" + curr.getTestID() + "'");

        while (xl.next()) {
            String components = xl.get(index);
            getComponentName();
            while (!"".contentEquals(cmpName)) {
                index++;
                System.out.println("Component: " + cmpName);
                getComponentName();

                Class<?> className = Class.forName("components.TestComponent");
                java.lang.reflect.Constructor<?> constructor = className.getDeclaredConstructor(Script.class, User.class);

                // Specify constructor parameters
                Object[] constructorParams = { scripts.get(currScriptIndex - 1), null };

                // Create an instance using the constructor with parameters
                Object obj = constructor.newInstance(constructorParams);
                Method method = className.getMethod("run");
                method.invoke(obj);
            }
        }
    }

    public void getComponentName() throws Exception {
        cmpName = xl.get(index);
    }
}