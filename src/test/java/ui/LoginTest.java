package ui;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DriverFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        WebDriver driver = DriverFactory.getDriver();

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        // Check presence of logout link to validate login
        boolean isLogoutPresent = driver.findElements(By.linkText("Log Out")).size() > 0;
        Assert.assertTrue(isLogoutPresent, "Login failed for: " + username);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        List<Object[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/testdata/loginData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading login test data file");
        }
        return data.toArray(new Object[0][0]);
    }
}
