package tests.ui;

import base.BaseTest;
import utils.CSVReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.LoggerUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends BaseTest {

    private static final org.apache.logging.log4j.Logger log = LoggerUtil.getLogger(LoginTest.class);


    @Test(dataProvider = "loginData")
    public void testLoginUsingDataProvider(String username, String password) {
        log.info("Login for user [{}]: {}", username, password);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        boolean isLogoutPresent = !driver.findElements(By.linkText("Log Out")).isEmpty();
        Assert.assertTrue(isLogoutPresent, "Login failed for user: " + username);
    }
    @Test
    public void testLoginUsingCSV() {
        String csvPath = "src/test/resources/testdata/loginData.csv"; // path to your CSV
        List<String[]> testData = CSVReader.readTestData(csvPath);

        for (String[] row : testData) {
            String username = row[0];
            String password = row[1];
            log.info("Testing login with: " + username + " / " + password);

            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.xpath("//input[@value='Log In']")).click();

            boolean isLogoutPresent = !driver.findElements(By.linkText("Log Out")).isEmpty();
            Assert.assertTrue(isLogoutPresent, "Login failed for user: " + username);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        List<Object[]> data = new ArrayList<>();
        String filePath = "src/test/resources/testdata/loginData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    data.add(new Object[]{credentials[0].trim(), credentials[1].trim()});
                }
            }
        } catch (Exception e) {
            throw new CustomRunTimeException("Error reading login data from: " + filePath, e);
        }

        return data.toArray(new Object[0][0]);
    }
}
