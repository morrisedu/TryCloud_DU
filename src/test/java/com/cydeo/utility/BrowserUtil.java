package com.cydeo.utility;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class BrowserUtil {
    /**
       * Waiting for a number of seconds with handling excpetion
        @param seconds
     */

    public static void waitFor(int seconds) {
        try {
            Thread.sleep( (long) seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get text from a list of web elements
     * @param webelements
     * @return
     */
    public static List<String> getTextFromWebElements(List<WebElement> webelements) {
        return webelements.stream().map(elm -> elm.getText()).collect(Collectors.toList());
    }
}
