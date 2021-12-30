package com.trycloud.steps;

import com.trycloud.pages.FilesPage;
import com.trycloud.pages.Navigation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.trycloud.utility.BrowserUtil.waitFor;
import static com.trycloud.utility.Driver.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.in;
import static org.junit.jupiter.api.Assertions.*;

public class FilesModuleStepsDef {
    FilesPage files = new FilesPage();

    List<String> favorite_files;

    void getFilesInFavorite() {
        favorite_files = getDriver().findElements(By.className("innernametext")).stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("favorite_files = " + favorite_files);
    }

    String favorited_file;

    @And("user navigates to the {string} module")
    public void userNavigatesToTheModule(String nav) {
        Navigation.goToLink(nav);
    }

    @Then("user should be redirected to the files module page")
    public void userShouldBeAbleToAccessThePage() {
        assertTrue(getDriver().findElement(By.xpath("//a[.='All files']")).isDisplayed());
    }

    @When("user clicks on the select all files button")
    public void userClicksOnTheSelectAllFilesButton() {
        files.selectAllFiles();
    }

    @Then("all uploaded files should be selected")
    public void allUploadedFilesShouldBeSelected() {

        waitFor(30);

        List<WebElement> all_files = getDriver().findElements(By.xpath("//input[@class='selectCheckBox checkbox']"));

        for (WebElement file : all_files) {
            assertTrue(file.isSelected());
        }
    }

    @When("user adds file to favourites")
    public void userAddsFileToFavourites() {
        files.goTo("Favorites");

        getFilesInFavorite();

        files.goTo("All files");

        List<WebElement> all_files = getDriver().findElements(By.className("ui-droppable"));
        System.out.println("all_files.size() = " + all_files.size());

        /*
            There are multiple ways you could check if a file is in favorites:
                1. Using the span favorite element
                2. Comparing names from the favorites page to elements in all files
         */

        for (int i = 1; i < all_files.size(); i++) {
//            try {
//                getDriver().findElement(By.xpath("//tbody[@id='fileList']/tr[" + i + "]//span[.='Favorited']"));
//            } catch (NoSuchElementException ex) {
//                getDriver().findElement(By.xpath("//tbody[@id='fileList']/tr[" + i + "]//span[@class='fileactions']/a[2]")).click();
//                getDriver().findElement(By.xpath("//*[@id='rightClickMenu']/ul/li[1]/a/span[2]")).click(); // Add file to favorites
//
//                // Get the name of the file added to favorites
//                favourited_file = getDriver().findElement(By.xpath("//tbody[@id='fileList']/tr[" + i + "]//span[@class='fileactions']/a[2]")).getText();
//                return;
//            }

            String check_file = getDriver().findElement(By.xpath("//*[@id='fileList']/tr[1]/td[2]/a/span[1]/span")).getText();

            for (String favorite_file : favorite_files) {
                if (favorite_file.equals(check_file)) {
                } else {
                    getDriver().findElement(By.xpath("//tbody[@id='fileList']/tr[" + i + "]//span[@class='fileactions']/a[2]")).click();
                    getDriver().findElement(By.xpath("//*[@id='rightClickMenu']/ul/li[1]/a/span[2]")).click(); // Add file to favorites

                    // Get the name of the file added to favorites
                    favorited_file = getDriver().findElement(By.xpath("//tbody[@id='fileList']/tr[" + i + "]//span[@class='fileactions']/a[2]")).getText();
                    System.out.println("favorited_file = " + favorited_file);
                }
            }
        }
    }

    @And("user goes to the {string} menu")
    public void userGoesToTheMenu(String files_submenu) {
        files.goTo(files_submenu);
    }

    @Then("the file should be amount the favourites")
    public void theFileShouldBeAmountTheFavourites() {
        waitFor(5);
        getFilesInFavorite();
        waitFor(5);

        assertThat(favorited_file, in(favorite_files));
    }
}
