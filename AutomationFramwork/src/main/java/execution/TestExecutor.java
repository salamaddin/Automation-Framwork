package execution;

import java.lang.reflect.Method;

import Lib.Files.FilloXL;
import Lib.Files.Scripts;

public class TestExecutor {

    public static void executeTests() {
        try {
            FilloXL fillo = new FilloXL();
            Scripts scripts = new Scripts();

            while (scripts.hasNext()) {
                String className = fillo.getData("C:/Users/arka.sarkar/Desktop/temp/MasterDriver.xlsx", "Scripts", "ClassName", "");
                executeTest(className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeTest(String className) {
        try {
            Class<?> testClass = Class.forName(className);
            Object testInstance = testClass.getDeclaredConstructor().newInstance();

            testClass.getMethod("executeTest").invoke(testInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        executeTests();
    }
}