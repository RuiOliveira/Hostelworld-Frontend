package hostelworld.bdd;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rui Manuel Oliveira on 03/06/2017.
 */

public class AvailableProperties {

    public static final String HTTP_WWW_HOSTELWORLD_COM = "http://www.hostelworld.com/";
    private static WebDriver driver;


    /**
     * Description: Open browser and select the resolution to test --> input = full, medium or small
     */
    @Given("^I am in Hostelworld home page with \"([^\"]*)\"$")
    public void accessPage(String windowSize) throws InterruptedException {

        driver = new FirefoxDriver();

        driver.get(HTTP_WWW_HOSTELWORLD_COM);

        if(windowSize.equals("full")){
            driver.manage().window().maximize();
        }
        else if(windowSize.equals("medium")){
            driver.manage().window().setSize(new Dimension(768, 1024));
        }
        else if(windowSize.equals("small")){
            driver.manage().window().setSize(new Dimension(412, 732));
        }

    }

    /**
     * Description: Search from a value and select a value in dropdown
     */
    @Then("^search \"([^\"]*)\" in input search$")
    public void search(String Inputsearch) throws InterruptedException {

        WebElement search = driver.findElement(By.id("home-search-keywords"));
        search.click();
        search.clear();
        search.sendKeys(Inputsearch);
        Thread.sleep(2000);
        search.sendKeys(Keys.ENTER);

    }

    /**
     * Description: Press Let´s Go button and wait button
     */
    @And("^I press search button$")
    public void pressSearchButton() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[@id=\"sb-search\"]/div[3]/div/button")));

        driver.findElement(By.xpath("//*[@id=\"sb-search\"]/div[3]/div/button"));

        ////div[@class='row']//div[@class='small-12 medium-12 large-12 columns']//button
    }

    /**
     * Description: Check if search result page is open through of presence od some element
     */
    @Then("^I expected to be in results page$")
    public void resultsPage() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@class='pageheader-overlay']")));

    }

    /**
     * Description: Sort by Name the results and validate if they are sorted
     */
    @And("^I sort the results by \"([^\"]*)\"$")
    public void sorteBy(String sort) throws InterruptedException {

        //Scrool down
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,500)", "");

        Thread.sleep(5000);

        //Click in sort button
        driver.findElement(By.xpath("//dd[@class='topfilter rounded sort-button']")).click();

        //Select Name button
        driver.findElement(By.id("sortByName")).click();

        Thread.sleep(2000);

        //Validate if the results are sorted
        ArrayList<String> obtainedList = new ArrayList<String>();
        List<WebElement> elementsDiv =  driver.findElements(
                By.xpath("//h2//a[@class='hwta-property-link' and   @target='_self']"));
        for(WebElement data:elementsDiv){
            //System.out.println(data.getText());
            obtainedList.add(data.getText());
        }

        ArrayList<String> sortedList = new ArrayList<String>();
        for(String s:obtainedList){
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Assert.assertTrue(sortedList.equals(obtainedList));

    }

    /**
     * Description: Close the browser
     */
    @Then("^I close the browser$")
    public void closeBrowse() throws InterruptedException {

        driver.close();

    }
}
