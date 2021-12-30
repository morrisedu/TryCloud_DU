package com.trycloud.pages;

import org.openqa.selenium.support.PageFactory;

import static com.trycloud.utility.Driver.getDriver;

public class PageCommons {
    public PageCommons() {
        PageFactory.initElements(getDriver(), this);
    }
}
