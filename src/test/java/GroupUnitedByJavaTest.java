import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupUnitedByJavaTest {

    private static final String URL_SELENIUM_TEST_PAGE = "https://www.selenium.dev/selenium/web/web-form.html";
    private static final String URL_GOOGLE_COM = "https://www.google.com";

    @Test
    public void testDoubleClick() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        WebElement elementsPage = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsPage.click();
        WebElement buttons = driver.findElement(By.xpath("//span[@class='text' and text()='Buttons']"));
        buttons.click();

        WebElement doubleClickMe = driver.findElement(By.id("doubleClickBtn"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickMe).perform();

        String doubleClickMessage = driver.findElement(By.id("doubleClickMessage")).getText();

        Assert.assertEquals(doubleClickMessage, "You have done a double click");

        driver.quit();
    }

    @Test
    public void testText() {

        WebDriver driver = new ChromeDriver();
        driver.get(URL_SELENIUM_TEST_PAGE);

        WebElement selectDropdown = driver.findElement(By.name("my-select"));
        selectDropdown.click();

        WebElement selectOption = driver.findElement(By.cssSelector("[value='2']"));
        selectOption.click();

        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();

        WebElement messageText = driver.findElement(By.id("message"));

        Assert.assertEquals(messageText.getText(),"Received!");
        driver.quit();
    }

    @Test
    public void testCheck() {

        WebDriver driver = new ChromeDriver();
        driver.get(URL_SELENIUM_TEST_PAGE);

        WebElement checkInput = driver.findElement(By.id("my-check-2"));
        checkInput.click();

        Assert.assertEquals(checkInput.getDomProperty("checked"),"true");

        driver.quit();
    }

    @Test
    public void testSelectingOption() {

        WebDriver driver = new ChromeDriver();

        driver.get(URL_SELENIUM_TEST_PAGE);

        WebElement dropdownSelect = driver.findElement(By.cssSelector("[name='my-select']"));
        Select select = new Select(dropdownSelect);
        select.selectByVisibleText("Three");

        WebElement threeOption = driver.findElement(By.cssSelector("[value='3']"));
        Assert.assertTrue(threeOption.isSelected());

        driver.quit();
    }

    @Test
    public void testSortingPrice() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement loginInput = driver.findElement(By.id("user-name"));
        loginInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement sortingSelect = driver.findElement(By.className("product_sort_container"));
        sortingSelect.click();

        WebElement lohiOption = driver.findElement(By.cssSelector("[value='lohi']"));
        lohiOption.click();

        List<WebElement> priceList = driver.findElements(By.className("inventory_item_price"));

        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement: priceList) {
            String priceText = priceElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(prices,sortedPrices);

        driver.quit();
    }

    @Test
    public void testTitleGoogle() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String expectedTitle = "Google";

        driver.get(URL_GOOGLE_COM);

        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);

        driver.quit();
    }

    @Test
    public void testSearchBar() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String expectedURL = "https://www.google.ru/?hl=ru";

        driver.get(URL_GOOGLE_COM);

        WebElement searchBar = driver.findElement(By.xpath("//textarea[@title='Поиск']"));
        searchBar.sendKeys("Google");
        searchBar.sendKeys(Keys.ENTER);

        WebElement lineResponse = driver.findElement(By.xpath("(//h3[.='Google'])[1]"));
        lineResponse.click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        driver.quit();
    }

    @Test
    public void testAddToCardBag() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement setUserName = driver.findElement(By.id("user-name"));
        setUserName.click();
        setUserName.sendKeys("standard_user");

        WebElement setPassword = driver.findElement(By.id("password"));
        setPassword.click();
        setPassword.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();

        WebElement getBag = driver.findElement(By.id("item_4_title_link"));
        getBag.click();

        WebElement buttonAddToCard = driver.findElement(By.name("add-to-cart"));
        buttonAddToCard.click();

        WebElement buttonBasket = driver.findElement(By.className("shopping_cart_link"));
        buttonBasket.click();

        WebElement text = driver.findElement(By.xpath("//*[@data-test=\"inventory-item-name\"]"));

        Assert.assertEquals(text.getText(), "Sauce Labs Backpack");

        driver.quit();
    }

    @Test
    public void testCheckRegisterFields() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        driver.manage().window().fullscreen();

        final List<String> expectedFields = List.of("First Name:", "Last Name:", "Address:",
                "City:", "State:", "Zip Code:", "Phone #:", "SSN:", "Username:", "Password:", "Confirm:");

        List<WebElement> fieldsElements = driver.findElements(By.xpath("//tbody/tr/td/b"));

        List<String> actualFields = fieldsElements.stream().map(WebElement::getText).toList();

        Assert.assertEquals(actualFields, expectedFields);

        driver.quit();
    }
}
