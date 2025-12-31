package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {

    private Properties properties;

    public WebDriver driver = null;


    public void createDriver() {

        properties = new Properties();
        InputStream file = null;

        try {
            file = Files.newInputStream(Paths.get("globalProperties.properties"));
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // se no terminal mandar outro browser
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") :properties.getProperty("browser");
        String timeout = properties.getProperty("timeout");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("chromeHeadless")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().setSize(new Dimension(1920, 1080));

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(timeout)));
        driver.manage().window().maximize();
    }


    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String to HashMap- Jackson Databind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

        //{map, map}

    }

    public String getScreenshot(String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File filePath = new File(
                System.getProperty("user.dir") + "/src/test/resources/reports/" + testName + ".png"
        );
        FileUtils.copyFile(src, filePath);

        return System.getProperty("user.dir") + "src/test/resources/reports/" + testName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() {
        createDriver();
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    @AfterMethod(alwaysRun = true)
    public void finish() {
        driver.quit();
    }

}
