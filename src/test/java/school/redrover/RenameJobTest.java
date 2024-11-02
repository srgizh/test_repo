package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class RenameJobTest extends BaseTest {

    @Test
    public void tesRenameJob() {

        createJob("TestBuild");

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='job_TestBuild']/td[3]/a/span")).getText(),"TestBuild");

        renameJob("TestBuild_NewName");

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='jenkins-table__link model-link inside']"))
                .getText(), "TestBuild_NewName");

    }

    public void createJob( String projectName) {

        getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a")).click();
    }

    public void renameJob(String testBuildNewName){
        getDriver().findElement(By.xpath("//*[@id='job_TestBuild']/td[3]/a/span")).click();
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[7]/span/a")).click();
        WebElement inputField = getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[2]/input"));
        inputField.clear();
        inputField.sendKeys(testBuildNewName);
        WebElement renameButton = getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button"));
        Actions actionsRenameButton = new Actions(getDriver());
        actionsRenameButton.moveToElement(renameButton).click().perform();
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }


}
