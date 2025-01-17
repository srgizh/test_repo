package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject4Test extends BaseTest {

    @Test
    public void createNewFreestyleProject() throws InterruptedException {
        String projectName = "New_Freestyle_Project";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@class='jenkins-input']")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String actualName = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText();

        Assert.assertEquals(actualName, projectName);
    }
}


