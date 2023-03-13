package net.absoft.andreybuha.automationcourse;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class practiseBrowserWindowsTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments("--window-size=1920,1080", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/browser-windows");
    }


    @Test
    public void testBrowserWindows() {
        String windowHandle = driver.getWindowHandle();
        driver.findElement(By.id("tabButton")).click();

        String windowSample = driver.getWindowHandles().stream().filter(handle -> !handle.equals(windowHandle))
                .findFirst()
                .orElseThrow(() -> new AssertionError("New windows handle was not added"));

        driver.switchTo().window(windowSample);

        assertEquals(driver.findElement(By.id("sampleHeading")).getText(), "This is a sample page", "Test is failed");
        driver.switchTo().window(windowHandle);
    }

    @AfterMethod
    public void afterMethod() {
       driver.quit();
    }
}
