package tests.day7;

import org.testng.annotations.*;
import org.testng.Assert;

public class AnnotationsTest {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
         @BeforeMethod
         public void setup(){
             System.out.println("Before method!");
         }
         @AfterMethod
         public void teardown(){
             System.out.println("After Method");
         }
    @Test
    public void test1() {
        System.out.println("Test 1");
        Assert.assertTrue(5 == 5);
    }

    @Test
    public void test2() {
        System.out.println("Test 2!");
    }

    @Test
    public void test3() {
        System.out.println("Test 3!");

    }
}