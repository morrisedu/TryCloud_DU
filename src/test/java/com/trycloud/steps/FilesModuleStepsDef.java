package com.trycloud.steps;

import com.github.javafaker.Faker;
import com.trycloud.pages.FilesPage;
import com.trycloud.pages.Navigation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
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

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

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

    interface utilityFunction {
        void addFileToFavorite();
    }

    @When("user adds file to favorites")
    public void userAddsFileToFavorites() {
        files.goTo("Favorites");

        getFilesInFavorite();

        files.goTo("All files");

        List<WebElement> all_files = getDriver().findElements(By.xpath("//*[@class='innernametext']")); // Get all files that are available
        System.out.println("all_files.size() = " + all_files.size());

        /*
            There are multiple ways you could check if a file is in favorites:
                1. Using the span favorite element
                2. Comparing names from the favorites page to elements in all files
         */

        // Check if file is in Favorite. If file is not in favorite, Add file to favorite
        for (WebElement all_file : all_files) {
            favorited_file = all_file.getText();
            try {
                if (favorite_files.contains(favorited_file)) {
                    continue;
                } else {
                    // Add file to favorite
                    new Actions(getDriver()).contextClick(getDriver().findElement(By.xpath("//*//*[@class='innernametext' and text()=" + favorited_file))).perform(); // Right click to get context menu

                    getDriver().findElement(By.xpath("//*[@id='rightClickMenu']//li[1]/a")).click(); // Add the file to favorite
                }
            } catch (Exception ex) {
                // Create file & add it to favorite
                getDriver().findElement(By.xpath("//*[@id='controls']/div[2]/a")).click();

                getDriver().findElement(By.xpath("//*[@id='controls']/div[2]/div[2]/ul/li[2]/a")).click();

                favorited_file = new Faker().artist().name();

                getDriver().findElement(By.xpath("//*[@id='view21-input-folder']")).sendKeys(favorited_file + Keys.ENTER);

                waitFor(2);

                new Actions(getDriver()).contextClick(getDriver().findElement(By.xpath("//*//*[@class='innernametext' and text()=" + favorited_file))).perform(); // Right click to get context menu

                getDriver().findElement(By.xpath("//*[@id='rightClickMenu']//li[1]/a")).click(); // Add the file to favorite
            }
        }
    }

    @And("user goes to the {string} menu")
    public void userGoesToTheMenu(String files_submenu) {
        wait.until(ExpectedConditions.elementToBeClickable(files.getFavorite_files()));
        files.goTo(files_submenu);
    }

    @Then("the file should be added to favorites")
    public void theFileShouldBeAddedToFavourites() {
        waitFor(5);
        getFilesInFavorite();

        waitFor(5);

        assertThat(favorited_file, in(favorite_files));
    }

    /**
     * Method to get number of files in favorite files menu
     * @return the number of files favorited
     */
    int howManyInFavoriteFiles() {
        return getDriver().findElements(By.className("filename")).size();
    }

    @And("there are files present in favorites")
    public void thereAreFilesPresentInFavorites() {
        if (howManyInFavoriteFiles() == 0) {
            userAddsFileToFavorites();
        }
    }

    @When("user removes a file from favorites")
    public void userRemovesAFileFromFavorites() {

    }

    @Then("the file should be removed from favorites")
    public void theFileShouldBeRemovedFromFavorites() {
    }
}
