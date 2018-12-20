package vbs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;

public class ScannerHappyPath {
    WebDriver driverOperator;
    WebDriver driverDevice;

    @Test
    public void IDScannerTest(){
        System.out.println("Starting test");
        //Fill form and login
        driverOperator.findElement(By.id("username")).sendKeys("mitropoulos");
        driverOperator.findElement(By.id("password")).sendKeys("Geo123456");
        driverOperator.findElement(By.xpath("//button[@type='submit']")).click();

        //Open customer's browser
        driverDevice.get("https://grtvap03.korrdot.com/korrDevice");

        WebElement element;
        WebDriverWait wait = new WebDriverWait(driverDevice, 10);
        element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']")));


        driverDevice.findElement(By.xpath("//button[@type='button']")).click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Open ID Scanner
        driverOperator.findElement(By.xpath("//div[@class='idwidget']//div//app-id-scanner//span[@class='inactivescanner'][contains(text(),'Inactive')]")).click();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Accept scanned document
        driverOperator.findElement(By.xpath("//button[@class='btn btn-default btn-accept btn-block']")).click();

        //Complete scanning
        driverOperator.findElement(By.xpath("//span[contains(text(),'STOP SCANNING')]")).click();

        //Characterize document
        driverOperator.findElement(By.xpath("//div[@class='radio-tile-group']//div[1]//div[1]//input[1]")).click();

    }
    @BeforeMethod
    public void setUp(){
        System.out.println("Setting up test");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\spduser\\Downloads\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

        driverOperator = new ChromeDriver(options);
        driverDevice = new ChromeDriver(options);

        driverOperator.get("https://grtvap03.korrdot.com/korrOperator/login");
    }
    @AfterMethod
    public void tearDown(){
        System.out.println("Closing test");

        String expectedURL = driverOperator.getCurrentUrl();
        String actualURL="https://grtvap03.korrdot.com/korrOperator/transaction";
        Assert.assertEquals(actualURL, expectedURL);
        System.out.println("Test PASS \n" +expectedURL);

        driverDevice.close();
        driverOperator.close();
    }
}
