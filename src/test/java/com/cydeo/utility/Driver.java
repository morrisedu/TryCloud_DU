package com.cydeo.utility;

/*
    This class will return a single driver object, regardless of the number of times it is called
    This class employs the Singleton pattern
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.cydeo.utility.ConfigReader.confRead;

public class Driver {
//    private static WebDriver obj;
    private static InheritableThreadLocal<WebDriver> driver_pool = new InheritableThreadLocal<>();
    private static String browser = confRead("browser");

    private Driver() {}

    /**
        Return object witj only one WebDriver instance
        Will return the same object if it already exists. It will only create a new one if it is null
    */
    public static WebDriver getDriver() {
//        if (obj == null) {
//            switch (browser.toLowerCase()) {
//                case "chrome":
//                    WebDriverManager.chromedriver().setup();
//                    obj = new ChromeDriver();
//                    break;
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    obj = new FirefoxDriver();
//                    break;
//                case "edge":
//                    WebDriverManager.edgedriver().setup();
//                    obj = new EdgeDriver();
//                    break;
//                default:
//                    obj = null;
//                    break;
//            }
//
//            return obj;
//        } else {
//            return obj;
//        }
        switch (confRead("env")) {
            // Set to use local WebDriver
            case "dev":
                if (driver_pool.get() == null) {
                    switch (browser.toLowerCase()) {
                        case "chrome":
                            WebDriverManager.chromedriver().setup();
                            driver_pool.set(new ChromeDriver());
                            break;
                        case "firefox":
                            WebDriverManager.firefoxdriver().setup();
                            driver_pool.set(new FirefoxDriver());
                            break;
                        case "edge":
                            WebDriverManager.edgedriver().setup();
                            driver_pool.set(new EdgeDriver());
                            break;
                        default:
                            driver_pool.set(null);
                            System.err.println("Unknown browser type " + browser);
                            break;
                    }

                    return driver_pool.get();
                } else {
                    // If there is an existing one
                    return driver_pool.get();
                }

                // Set to use RemoteWebDriver
            case "prod":
                if (driver_pool.get() == null) {
                    try {
                        String grid_address = confRead("seleniumGridServer");
                        URL url = new URL("http://" + grid_address + ":4444/wd/hub");
                        DesiredCapabilities desired_capabilities = new DesiredCapabilities();
                        desired_capabilities.setBrowserName(confRead("browser"));
                        RemoteWebDriver driver = new RemoteWebDriver(url, desired_capabilities);
                        driver_pool.set(driver);
                    } catch (Exception ex) {
                        driver_pool.set(null);
                        ex.printStackTrace();
                    }
                    return driver_pool.get();
                } else {
                    // If there is an existing one
                    return driver_pool.get();
                }
            default:
                System.err.println("Invalid environment");
                driver_pool.set(null);
                return driver_pool.get();
        }
    }

    /**
        Quitting the browser and resetting the value of WebDriver instance to null to be able to reuse already quit driver
     */
    public static void closeBrowser() {
        /**
            1. Check if there is a driver instance or not i.e. check whether the obj is null or not
            2. if obj isn't null, quit browser & make it null
            ** Once quit, the driver cannot be used again
         */
        if (driver_pool.get() != null) {
            driver_pool.get().quit();
            driver_pool.set(null);
        }
    }
}
