package whatsappSelenium;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SeleniumComponent {


    public void sendMessageToWhatsappGroup(String groupIdentifier, String message) {
        System.setProperty("webdriver.chrome.driver", "src/main/java/whatsappSelenium/chromedriver103.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("-remote-debugging-port=9014");
        File file = new File("src/main/resources/chromeWhatsappTestProfile");
        String fullyQualifiedPath = file.getAbsolutePath();
        System.out.println(fullyQualifiedPath);
        options.addArguments("--user-data-dir=" + fullyQualifiedPath);
        options.getCapabilityNames().forEach(System.out::println);
        WebDriver driver = new ChromeDriver(ChromeDriverService.createDefaultService(), options);
        driver.get(WhatsappUris.whatsappUri);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@title=\"" + groupIdentifier + "\"]"))));

        //*[@title="HAMON Organisation"]

        elem.click();
        wait.until(SeleniumComponent::tenSecondsAreOver);


        //driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys("Das ist mein pc der einen Test schickt :D lol");

        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys(message);
        wait.until(SeleniumComponent::tenSecondsAreOver);

        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys("\n");

        wait.until(SeleniumComponent::tenSecondsAreOver);
        driver.close();
    }

    private static boolean tenSecondsAreOver(WebDriver webDriver) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


}



