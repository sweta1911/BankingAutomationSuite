package listeners;

import utils.ConfigReader;
import io.qameta.allure.Allure;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount;

    static {
        // Load retry count from config.properties
        String retryCountStr = ConfigReader.get("retryCount");
        int count = 0;
        try {
            count = Integer.parseInt(retryCountStr);
        } catch (NumberFormatException e) {
            System.err.println("Invalid retryCount in config.properties. Defaulting to 0.");
        }
        maxRetryCount = count;
    }

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            System.out.println("Retrying test: " + result.getName() + " | Attempt " + (retryCount + 1));

            Allure.addAttachment("Retry Info",
                    "Retrying test: " + result.getName() + " | Attempt " + (retryCount + 1));

            retryCount++;
            return retryCount <= maxRetryCount;
        }
        return false;
    }
}
