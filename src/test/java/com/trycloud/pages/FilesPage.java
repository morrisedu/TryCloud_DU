package com.trycloud.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.trycloud.utility.Driver.getDriver;

public class FilesPage {
    @FindBy(xpath = "//label[@for='select_all_files']")
    private WebElement select_all_files;

    // Files modules actions menu
    @FindBy(xpath = "//a[normalize-space(.)='All files']")
    private WebElement all_files;

    @FindBy(xpath = "//a[normalize-space(.)='Recent']")
    private WebElement recent_files;

    @FindBy(xpath = "//a[normalize-space(.)='Favorites']")
    private WebElement favorite_files;

    @FindBy(xpath = "//a[normalize-space(.)='Shares']")
    private WebElement shared_files;

    @FindBy(xpath = "//a[normalize-space(.)='Tags']")
    private WebElement file_tags;

    @FindBy(xpath = "//a[normalize-space(.)='Shared to Circles']")
    private WebElement shared_to_circles;

    @FindBy(xpath = "//a[normalize-space(.)='Deleted files']")
    private WebElement deleted_files;

    public FilesPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void selectAllFiles() {
        if (!select_all_files.isSelected()) {
            select_all_files.click();
        }
    }

    public void goTo(String page) {
        switch (page) {
            case "All files":
                all_files.click();
                break;
            case "Favorites":
                favorite_files.click();
                break;
            case "Recent":
                recent_files.click();
                break;
            case "Shares":
                shared_files.click();
            case "Tags":
                file_tags.click();
                break;
            case "Shared to Circles":
                shared_to_circles.click();
                break;
            case "Deleted files":
                deleted_files.click();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
