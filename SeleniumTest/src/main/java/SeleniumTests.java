import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;


public class SeleniumTests {

    private WebDriver driver;

    /**
     * Constructor
     */
    public SeleniumTests(){
        System.setProperty("webdriver.chrome.driver", "/Users/robertogonzales/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.ncl.com/");
    }

    /**
     * Navigates to the find Shore Excursions Page
     * @throws InterruptedException
     */
    public void navigateToShoreExcursionsPage() throws InterruptedException {
        Thread.sleep(1000);
        WebElement exploreDropDown = driver.findElement(By.xpath("//a[contains(text(),'Explore')]"));
        exploreDropDown.click();
        Thread.sleep(1000);
        WebElement shoreExcursionsButton = driver.findElement(By.xpath("//a[contains(text(),'Shore Excursions')]"));
        shoreExcursionsButton.click();
    }

    /**
     * Picks the desire destination and navigates to the Shore Excursions Page with the destinations as the filter
     * @param destinationName
     * @throws InterruptedException
     */
    public void pickDestinationByDestinationName(String destinationName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement searchDestinationDropDown = driver.findElement(By.xpath("//div[@id='search_destinations_chosen']/a/span"));
        searchDestinationDropDown.click();
        WebElement searchDestinationSelection = driver.findElement(By.xpath("//div[@class='chosen-drop']//li[contains(text(),'" + destinationName + "')]"));
        searchDestinationSelection.click();
        findExcursionsButtonClick();
    }

    /**
     * clicks on the Find Excursions Button
     */
    public void findExcursionsButtonClick(){
        WebElement findExcursionsButton = driver.findElement(By.xpath("//button[@type='submit']"));
        findExcursionsButton.click();
    }
    
    //TODO: 2/7/20 could potentially add options to add more ports if needed
    /**
     * Add a port filter to the filter widget
     * @param portName
     * @throws InterruptedException
     */
    public void filterByPort(String portName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement portAsideButton = driver.findElement(By.xpath("//aside[@id='sap-menu-left']/div/div[4]/ul/li[2]/a"));
        portAsideButton.click();
        Thread.sleep(1000);
        WebElement portsUL = driver.findElement(By.id("ports"));
        List<WebElement> portsList = portsUL.findElements(By.tagName("li"));
        for (WebElement port : portsList) {
            if (port.getText().equals(portName)) {
                port.click();
            }
        }
        Thread.sleep(1000);
        portAsideButton.click();
    }

    public static void main(String[] args) throws InterruptedException {
        SeleniumTests shoreExcursionsTest = new SeleniumTests();
        shoreExcursionsTest.navigateToShoreExcursionsPage();
        shoreExcursionsTest.pickDestinationByDestinationName("Alaska Cruises");
        shoreExcursionsTest.filterByPort("Victoria, British Columbia");
    }

    // TODO: 2/7/20 need to handle any interruptions such as pop-ups but i did not have enough time to do it since this was short notice.
    // TODO: 2/7/20  : 2/7/20 could potentially add another method to find excursions by destination, port and/or activity guide if needed.
}
