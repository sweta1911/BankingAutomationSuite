package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    // Thread-local WebDriver instance
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        throw new IllegalStateException("DriverFactory class");
    }
    // Get WebDriver instance for current thread
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigReader.get("browser");
//            String browser = System.getProperty("browser", "chrome"); // default: chrome
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver(options));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver.set(new FirefoxDriver(firefoxOptions));

                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        driver.get().manage().window().maximize();
        int waitTime = Integer.parseInt(ConfigReader.get("implicitWait"));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        return driver.get();
    }

    // Quit WebDriver and remove from thread
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}