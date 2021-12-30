package com.trycloud.pages;

import org.openqa.selenium.By;

import static com.trycloud.utility.Driver.getDriver;

public class Navigation {


    /**
     * Method to click a link on the navigation
     * @param link_name is the name of the module you want to access. Should be in Camel Case.
     */
    public static void goToLink(String link_name) {
        getDriver().findElement(By.xpath("//ul[@id='appmenu']//a[normalize-space(.)='"+link_name+"']")).click();
    }

    public static boolean linkIsDisplayed(String link_name) {
        return getDriver().findElement(By.xpath("//ul[@id='appmenu']//a[normalize-space(.)='"+link_name+"']")).isDisplayed();
    }

}
