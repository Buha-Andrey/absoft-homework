package net.absoft.andreybuha.automationcourse;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class practiseSliderTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments("--window-size=1920,1080", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/slider");
    }

    @Test
    public void testSlider() {
        WebElement sliderDot = driver.findElement(By.xpath("//span/div[@style = 'left: calc(25% + 5px);']"));
        WebElement slider = driver.findElement(By.xpath("//span/input"));

        int usersPresentOfSlider = 1; // Users' percent of slider. Please enter 0-100
        int valueForCalc = usersPresentOfSlider - 50;

        int width=slider.getSize().getWidth();
        int xOffset = (width*(valueForCalc))/100;
        if (usersPresentOfSlider > 75 || usersPresentOfSlider < 36) {
            xOffset = xOffset - xOffset/40;
        } // i dont know why, but 1-35 and 76-100 need to add some increment for width value
        new Actions(driver).dragAndDropBy(slider, xOffset,0 ).perform();


        String inputSliderStr = driver.findElement(By.id("sliderValue")).getAttribute("value");
        int inputSliderInt = Integer.parseInt(inputSliderStr);    // finding integer value of input

        assertEquals(inputSliderInt, usersPresentOfSlider, "Error. values are not equal");

        System.out.println(width);
        System.out.println(usersPresentOfSlider);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
