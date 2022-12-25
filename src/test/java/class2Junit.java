import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class class2Junit {

    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("web-driver.gecko.driver", "./src/test/resources/geckodriver.exe");
        driver=new FirefoxDriver( );
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));  }
@Test
    public void submitFrom(){
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Md. Rabbi");
        driver.findElement(By.id("userEmail")).sendKeys("Test123456@gmail.com");
        driver.findElement(By.cssSelector("[id=currentAddress]")).sendKeys("dhaka");
        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void submitFromWitTag(){
        driver.get("https://demoqa.com/text-box");
      List<WebElement > textEleme= driver.findElements(By.tagName("input"));
      textEleme.get(0).sendKeys("Md.rabbi");
      textEleme.get(1).sendKeys("mon@gamil.com");

    }
@Test
    public void buttonClick(){
        driver.get("https://demoqa.com/buttons");
        Actions action=new Actions(driver);
        //WebElement button1= driver.findElement(By.id("doubleClickBtn"));
      // action.doubleClick(button1).perform();
     List <WebElement> buttons= driver.findElements(By.cssSelector("[type=button]"));
    action.doubleClick(buttons.get(1)).perform();
    action.contextClick(buttons.get(2)).perform();
    action.click(buttons.get(3)).perform();

    List <WebElement> message= driver.findElements(By.tagName("p"));

    String message1actual=message.get(0).getText();
    String message1Expected="You have done a double click";
    Assert.assertTrue(message1actual.contains(message1Expected));

    String message2actual=message.get(1).getText();
    String message2Expected="You have done a right click";
    Assert.assertTrue(message2actual.contains(message2Expected));



    String message3actual=message.get(2).getText();
    String message3Expected="You have done a dynamic click";
    Assert.assertTrue(message3actual.contains(message3Expected));

   }
@Test

   public  void handeAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        Thread.sleep(200);
        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();
        String messageActualAlert =driver.findElement(By.id("confirmResult")).getText();
        String messageExpectedAlert="You selected Cancel";
        Assert.assertTrue(messageActualAlert.contains(messageExpectedAlert));


        driver.findElement(By.id("promtButton")).click();
        driver.switchTo().alert().sendKeys("test");
        driver.switchTo().alert().accept();



   }
   @Test
   public void selectDate(){
        driver.get("https://demoqa.com/date-picker");
        driver.findElement(By.id("datePickerMonthYearInput")).clear();
       driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("12/22/2022");
       driver.findElement(By.id("datePickerMonthYearInput")).click();
       driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);

   }

@Test
   public void selectDateBykeyboard(){
       driver.get("https://demoqa.com/date-picker");
       Actions actions  =new Actions(driver);
       WebElement datePicker= driver.findElement(By.id("datePickerMonthYearInput"));
     /*  actions.moveToElement(datePicker);
       actions.doubleClick(datePicker).
               click().
               keyDown(Keys.BACK_SPACE).
               keyUp(Keys.BACK_SPACE).
               perform(); */
    datePicker.sendKeys(Keys.CONTROL+"a");
    datePicker.sendKeys(Keys.BACK_SPACE);

   }


@Test
   public  void selectDropdown(){
       driver.get("https://demoqa.com/select-menu");
       WebElement dropDown=driver.findElement(By.id("oldSelectMenu"));
       Select option=new Select(dropDown);
       //option.selectByIndex(1);
       option.selectByValue("4");
       option.selectByVisibleText("White");

   }

    @Test
    public  void selectMultipleDropdown(){
        driver.get("https://demoqa.com/select-menu");
        WebElement dropDown=driver.findElement(By.name("cars"));
        Select option=new Select(dropDown);
        if(option.isMultiple()){
            option.selectByVisibleText("Saab");
            option.selectByVisibleText("Audi");
        }
    }
    @Test
  public void hoverManu(){
        driver.get("https://green.edu.bd/");
      //  driver.findElements(By.xpath("//a[contains(text().'ABOUT US')]"));
       List <WebElement> manuAboutelement= driver.findElements(By.xpath("//a[contains(text(),\"About Us\")]"));

     Actions action =new Actions(driver);
     action.moveToElement(manuAboutelement.get(2)).perform();
    }

    @Test
    public void takeScreenShot( ) throws IOException{
        driver.get("https://demoqa.com/");
        Utils.takeScreenShot(driver);

    }
    @Test
    public void uploadImage(){
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadElement = driver.findElement(By.id("uploadFile"));
        uploadElement.sendKeys("E:\\123602.jpg");
        String text= driver.findElement(By.id("uploadedFilePath")).getText();
        Assert.assertTrue(text.contains("123602.jpg"));
    }
  @Test
    public void downloadIamge(){
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("downloadButton")).click();
  }
  @Test
   public void handleMultiplewindow() throws InterruptedException {
       driver.get("https://demoqa.com/browser-windows");
       driver.findElement(By.id("tabButton")).click();
       Thread.sleep(3000);
       ArrayList<String> w = new ArrayList(driver.getWindowHandles());
       driver.switchTo().window(w.get(1));
       System.out.println("New tab title: " + driver.getTitle());
       String text = driver.findElement(By.id("sampleHeading")).getText();
       Assert.assertEquals(text,"This is a sample page");
       driver.close();
       driver.switchTo().window(w.get(0));

   }
   @Test
   public void childWindowHandaler(){
       driver.get("https://demoqa.com/browser-windows");
       //WebDriverWait wait = new WebDriverWait(driver, 30);
       driver.findElement(By.id(("windowButton"))).click();
       String mainWindowHandle = driver.getWindowHandle();
       Set<String> allWindowHandles = driver.getWindowHandles();
       Iterator<String> iterator = allWindowHandles.iterator();

       while (iterator.hasNext()) {
           String ChildWindow = iterator.next();
           if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
               driver.switchTo().window(ChildWindow);
               String text = driver.findElement(By.id("sampleHeading")).getText();
               Assert.assertTrue(text.contains("This is a sample page"));
           }
       }
       driver.switchTo().window(mainWindowHandle);
       driver.close();
    }
    @Test
     public void  editWebtable(){
        driver.get("https://demoqa.com/webtables");
        driver.findElement(By.id("edit-record-1")).click();
        driver.findElement(By.id("firstName")).clear();
         driver.findElement(By.id("firstName")).sendKeys("rabbi");
         driver.findElement(By.id("submit")).click();
     }
     @Test
     public void scrabDataWebtable(){
         driver.get("https://demoqa.com/webtables");
         WebElement table = driver.findElement(By.className("rt-tbody"));
         List<WebElement> allRows = table.findElements(By.className("rt-tr"));
         int i=0;   for (WebElement row : allRows) {
             List<WebElement> cells = row.findElements(By.className("rt-td"));
             for (WebElement cell : cells) {        i++;
             System.out.println("num["+i+"] "+ cell.getText());   }    }
    }

@Test
      public void handleIframe(){
          driver.get("https://demoqa.com/frames");
          driver.switchTo().frame("frame2");
          String text=driver.findElement(By.id("sampleHeading")).getText();
          Assert.assertTrue(text.contains("This is a sample page"));
          driver.switchTo().defaultContent();

      }







}
