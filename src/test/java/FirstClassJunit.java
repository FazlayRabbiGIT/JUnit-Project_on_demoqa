import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class FirstClassJunit {
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("web-driver.gecko.driver", "./src/test/resources/geckodriver.exe");
        driver=new FirefoxDriver( );
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));  }
    @Test
    public void getTitle() {
        driver.get("https://demoqa.com/");
        String titleActual =driver.getTitle();
        String titleExpected="ToolsQA";
        System.out.println(titleActual);
        Assert.assertEquals(titleExpected,titleActual);

    }
    @Test
     public  void chackIfImageexits(){
         driver.get("https://demoqa.com/");
       // WebElement ImageElement= driver.findElement(By.xpath("//header/a[1]/img[1]"));
       //boolean statusActual= ImageElement.isDisplayed();
      // Assert.assertEquals(statusActual,true);
        WebElement imageElement=driver.findElement(By.xpath("//header/a[1]/img[1]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(imageElement));



    }







}
