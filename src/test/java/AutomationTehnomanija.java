import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AutomationTehnomanija {
    WebDriver driver;


    @BeforeMethod


    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDown (){
     //   driver.quit();

    }
    @Test
    @Parameters({"categoryItem", "subCategoryItem", "filter", "filterValue", "filter2", "filterValue2"})
    public void automationTest(@Optional String categoryItem,@Optional String subCategoryItem, String filter, String filterValue, String filter2, String filterValue2) throws InterruptedException {

        driver.get("https://www.tehnomanija.rs/");
        selectSubCategory(categoryItem,subCategoryItem);
        Thread.sleep(3000);
        selectFilter(filter,filterValue);
        Thread.sleep(3000);
        selectFilter(filter2,filterValue2);

    }




    public void selectSubCategory(String categoryItem, String subCategoryItem){
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(By.xpath("//a[text()='" + categoryItem + "']")))//Category
                .moveToElement(driver.findElement(By.xpath("//a[text()='" + subCategoryItem + "']")))//subCategoryItem
                .click()
                .build()
                .perform();
    }

    public void selectFilter(String filter, String filterValue){
        driver.findElement(By.xpath("//dt[@class='filter-options-title' and contains(text(), '"+filter+"')]/..//input[@value='"+filterValue+"']/..")).click();
}
//    public void selectFilter2 (String filter2,String filterValue2){
//        driver.findElement(By.xpath("//dt[@class='filter-options-title' and contains(text(), '"+filter2+"')]/..//input[@aria-label='"+filterValue2+"']/..")).click();
//    }
}
