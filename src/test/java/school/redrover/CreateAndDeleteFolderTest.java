package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.ProjectUtils;

public class CreateAndDeleteFolderTest extends BaseTest {

    @Test
    public void testDeleteFolder() {

        ProjectUtils.log("Create folder");
        getDriver()
                .findElement(By.xpath("//*[@id=\"tasks\"]/div[1]"))
                .click();

        getDriver()
                .findElement(By.xpath("//*[@id='name']"))
                .sendKeys("Some folder name");

        getDriver()
                .findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder"))
                .click();

        getDriver()
                .findElement(By.cssSelector("#ok-button"))
                .click();

        getDriver()
                .findElement(By.cssSelector("input.jenkins-input"))
                .sendKeys("Your advertisement could be in this field");

        getDriver()
                .findElement(By.cssSelector("textarea.jenkins-input"))
                .sendKeys("Advertising on websites from $5/month.\nCall as 555-55-55");

        getDriver()
                .findElement(By.cssSelector(".jenkins-submit-button"))
                .click();

        getDriver()
                .findElement(By.cssSelector("li.jenkins-breadcrumbs__list-item:nth-child(1) > a:nth-child(1)"))
                .click();

        String firstFolder = getDriver()
                .findElement(By.xpath("//a[@href='job/Some%20folder%20name/']"))
                .getText();

        Assert.assertEquals(firstFolder, "Your advertisement could be in this field");


        ProjectUtils.log("Delete folder");
        getDriver()
                .findElement(By.xpath("//*[@id=\"job_Some folder name\"]/td[3]/a"))
                .click();

        getDriver()
                .findElement(By.xpath("//*[@id=\"tasks\"]/div[4]"))
                .click();

        getDriver()
                .findElement(By.xpath("//*[@id=\"jenkins\"]/dialog/div[3]/button[1]"))
                .click();

        Assert.assertTrue(getDriver()
                .findElements(By.xpath("//*[@id=\"job_Some folder name\"]/td[3]/a"))
                .isEmpty());

        String welcomeStr = getDriver()
                .findElement(By.cssSelector(".empty-state-block > h1"))
                .getText();

        Assert.assertEquals(welcomeStr, "Welcome to Jenkins!");
    }
}