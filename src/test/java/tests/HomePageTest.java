package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArticlePageHelper;
import pages.HomePageHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class HomePageTest extends TestBase{
    ArticlePageHelper articlePage;

    @BeforeMethod
    public void initTests(){
        articlePage = PageFactory.initElements(driver, ArticlePageHelper.class);
    }

    @Test
    public void wikiTest(){
        //System.out.println("Search text:" + homePage.getSearchFieldText());
        Assert.assertEquals("Search Wikipedia", homePage.getSearchFieldText());
    }

    @Test
    public  void searchByTextOpenFirstArticleRotation() {

        homePage.searchBy("Java");

        System.out.println("First article title: " + homePage.getFirstArticleTitle());
        System.out.println("First article description: " + homePage.getFirstArticleDescription());
        homePage.firstFoundArticleOpen();
        articlePage.waitUntilPageIsLoaded();
        articlePage.returnLandScape();
        articlePage.waitUntilPageIsLoaded();
        articlePage.returnPortrait();

        Assert.assertEquals("Java", articlePage.getArticleTitle());
        Assert.assertEquals("Indonesian island",articlePage.getArticleDescription());

    }

    @Test
    public  void searchByTextOpenFirstArticleBackGround() {
        homePage.searchBy("Java");
        System.out.println("First article title: " + homePage.getFirstArticleTitle());
        System.out.println("First article description: " + homePage.getFirstArticleDescription());
        homePage.firstFoundArticleOpen();

        articlePage.waitUntilPageIsLoaded();
        homePage.runBackGround(5);
        articlePage.waitUntilPageIsLoaded();


        Assert.assertEquals("Java", articlePage.getArticleTitle());
        Assert.assertEquals("Indonesian island",articlePage.getArticleDescription());

    }
    @Test
    public void ReadingListTest() throws InterruptedException {
        homePage.searchBy("Java");
        homePage.firstFoundArticleOpen();
        articlePage.waitUntilPageIsLoaded();
        articlePage.addingToReadingList();
        articlePage.gotItButton();
        articlePage.pressToOkButton();
        articlePage.waitUntilPageIsLoaded();
        driver.navigate().back();
        homePage.openMyReadingList();
        homePage.readingListArticle();
        articlePage.waitUntilPageIsLoaded();
        Assert.assertEquals("Java",homePage.getFirstArticleTitle());
        Assert.assertEquals("island of Indonesia", homePage.getFirstArticleDescription());





    }


}