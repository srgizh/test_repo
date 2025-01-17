package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PageElementsTest extends BaseTest {

    private void newItemsData(String itemName, String itemXpath){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys(itemName);
        getDriver().findElement(By.xpath(itemXpath)).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void newItem (){
        String newItemElement = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).getText();
        Assert.assertEquals(newItemElement, "New Item");
    }

    @Test
    public void buildHistory (){
        String buildHistory = getDriver().findElement(By.xpath("//*[@id='tasks']/div[2]/span/a")).getText();
        Assert.assertEquals(buildHistory, "Build History");
    }

    @Test
    public void manageJenkins (){
        String manageJenkins = getDriver().findElement(By.xpath("//*[@id='tasks']/div[3]/span/a")).getText();
        Assert.assertEquals(manageJenkins, "Manage Jenkins");
    }

    @Test
    public void myView (){
        String myView = getDriver().findElement(By.xpath("//*[@id='tasks']/div[4]/span/a")).getText();
        Assert.assertEquals(myView, "My Views");
    }

    @Test
    public void addDescription (){
        String addDescription = getDriver().findElement(By.xpath("//*[@id='description-link']")).getText();
        Assert.assertEquals(addDescription, "Add description");
    }

    @Test
    public void verifyversion (){
        String version = getDriver().findElement(By.xpath("//*[@id='jenkins']/footer/div/div[2]/button")).getText();
        Assert.assertEquals(version, "Jenkins 2.462.3");
    }

    @Test
    public void writeDescription (){
        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();
        getDriver().findElement(By.name("description")).sendKeys("The test");
        getDriver().findElement(By.name("Submit")).click();

        String getDescription = getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText();
        Assert.assertEquals(getDescription, "The test");
    }

    @Test
    public void newItemValidationName (){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[2]/div")).click();

        String validationText = getDriver().findElement(By.id("itemname-required")).getText();
        Assert.assertEquals(validationText, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void newItemValidationOKButton (){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        boolean state = getDriver().findElement(By.id("ok-button")).isEnabled();

        Assert.assertFalse(state);
    }

    @Test
    public void newPipeline (){
        newItemsData("PipeTest", "//*[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[2]/label");

        getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/section[1]/div[3]/div[1]/div/span/label")).click();
        getDriver().findElement(By.name("_.daysToKeepStr")).sendKeys("5");

        getDriver().findElement(By.name("Submit")).click();

        String result = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[1]/div[1]/h1")).getText();

        Assert.assertEquals(result, "PipeTest");
    }

    @Test
    public void newFreeStyleProject (){
        newItemsData("FreeStyleProjectTest", "//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/div[2]/label");

        getDriver().findElement(By.name("Submit")).click();

        String result = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[1]/div[1]/h1")).getText();

        Assert.assertEquals(result, "FreeStyleProjectTest");
    }

    @Test
    public void newMultiConfigurationProject(){
        newItemsData("MultiConfigurationProjectTest", "//*[@id='j-add-item-type-standalone-projects']/ul/li[3]/div[2]/label");

        getDriver().findElement(By.name("Submit")).click();

        String result = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();

        Assert.assertEquals(result, "Project MultiConfigurationProjectTest");
    }

    @Test
    public void newFolder(){
        newItemsData("newFolderTest", "//*[@id='j-add-item-type-standalone-projects']/ul/li[3]/div[2]/label");

        getDriver().findElement(By.id("jenkins")).sendKeys("Testing Folder");
        getDriver().findElement(By.name("Submit")).click();

        String result = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();

        Assert.assertEquals(result, "Project newFolderTest");
    }

    @Test
    public void newMultibranchPipeline(){
        newItemsData("newMultibranchPipelineTest", "//*[@id='j-add-item-type-nested-projects']/ul/li[2]/div[2]/label");

        getDriver().findElement(By.name("_.displayNameOrNull")).sendKeys("Testing MultibranchPipeline");
        getDriver().findElement(By.name("Submit")).click();

        String result = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();

        Assert.assertEquals(result, "Testing MultibranchPipeline");
    }

    @Ignore
    @Test
    public void newOrganizationFolder(){
        newItemsData("newOrganizationFolderTest", "//*[@id='j-add-item-type-nested-projects']/ul/li[3]/div[2]/label/span");

        getDriver().findElement(By.name("_.displayNameOrNull")).sendKeys("Testing OrganizationFolder");
        getDriver().findElement(By.name("Submit")).click();

        String result = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();

        Assert.assertEquals(result, "Testing OrganizationFolder");
    }

}
