package whatsappSelenium;

import java.io.File;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumComponent {


    public void sendMessageToWhatsappGroup(String groupIdentifier, String message) {
        System.setProperty("webdriver.chrome.driver", "src/main/java/whatsappSelenium/chromedriver103.exe");

        ChromeOptions options = new ChromeOptions();
        File profile = new File("src/main/resources/chromeWhatsappTestProfile");

        String fullyQualifiedPath = profile.getAbsolutePath();
        System.out.println(fullyQualifiedPath);

        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--hide-scrollbars");
        options.addArguments("--disable-gpu");
        options.addArguments("--user-data-dir=" + fullyQualifiedPath);
        options.addArguments("-remote-debugging-port=9014");
        WebDriver driver = new ChromeDriver(ChromeDriverService.createDefaultService(), options);


        try {
            driver.get(WhatsappUris.whatsappUri);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@title=\"" + groupIdentifier + "\"]"))));

            elem.click();

            WebElement textbox = driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));
            typeMessageWithSpecialChars(textbox, message);

            textbox.sendKeys("\n");

            wait.until(SeleniumComponent::tenSecondsAreOver);
            System.out.println("new matches were propagated");
        } finally {
            driver.close();
            driver.quit();
        }
    }

    private void typeMessageWithSpecialChars(WebElement textbox, String message) {
        String wordOrCommand[] = message.split("\\s");
        String commandPattern = "%(.*?)%";
        Pattern p = Pattern.compile(commandPattern);
        for (String wOc : wordOrCommand) {
            Matcher m = p.matcher(wOc);
            if (m.find()) {
                sendKeystroke(textbox, m.group(0));
            } else {
                textbox.sendKeys(wOc + " ");
            }

        }
    }

    private void sendKeystroke(WebElement textbox, String command) {
        switch (command) {
            case SeleniumKeyCommands.newLine:
                textbox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));
        }


    }

    private static boolean tenSecondsAreOver(WebDriver webDriver) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


}



