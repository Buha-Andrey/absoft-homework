package net.absoft.andreybuha.automationcourse;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class practiseFormTest {

    private WebDriver driver;
    @BeforeMethod
    private void setUp(){
        ChromeOptions options = new ChromeOptions().addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/automation-practice-form");

   }
    @Test
    public void fillTheForm(){
        WebElement moveToCheckboxes = driver.findElement(By.id("state"));
        WebElement moveToSubmit = driver.
                findElement(By.xpath("//li[@id='item-8']/span[contains(text(), 'Select Menu')]"));
        WebElement selectDropdown = driver.findElement(By.id("state"));
        WebElement selectCity = driver.findElement(By.id("city"));



        driver.findElement(By.id("firstName")).sendKeys("RingCentral");
        driver.findElement(By.id("lastName")).sendKeys("RingCentral-test");
        driver.findElement(By.id("userEmail")).sendKeys("gwtest@test.com");
        driver.findElement(By.xpath("//form//input[@id='gender-radio-1']/../label")).click();
        driver.findElement(By.xpath("//form//input[@id='userNumber']")).
                sendKeys("1234567890");
    /*
           WebElement birthField = driver.findElement(By.id("dateOfBirthInput"));
           birthField.clear();
           birthField.sendKeys("02/06/1982");
    */
        driver.findElement(By.id("subjectsInput")).sendKeys("English");
        driver.findElement(By.id("subjectsInput")).sendKeys(Keys.RETURN);
        new Actions(driver).moveToElement(moveToCheckboxes).perform();
        driver.findElement(By.xpath("//label[@for = 'hobbies-checkbox-1']")).click();
        driver.findElement(By.id("uploadPicture")).
                sendKeys("/Users/andrey.buha/Desktop/Screenshot 2023-02-07 at 10.55.46.png");
        driver.findElement(By.id("currentAddress")).sendKeys("Odesa, Ukraine");
        driver.findElement(By.xpath("//div[contains(text(), 'Widgets')]")).
                click(); //need to get access to field with dropdown
        new Actions(driver).moveToElement(moveToSubmit).perform();

        new Actions(driver).click(selectDropdown).sendKeys(Keys.RETURN).perform();
        new Actions(driver).click(selectCity).sendKeys(Keys.RETURN).perform();



        driver.findElement(By.id("userForm")).submit();
        assertEquals(driver.findElement(By.id("example-modal-sizes-title-lg")).getText(), "Thanks for submitting the form", "Test is failed");


    }

    @AfterMethod
    public void afterMethod() {
            driver.quit();
    }
}
