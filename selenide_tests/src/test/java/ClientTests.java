import static com.codeborne.selenide.Configuration.assertionMode;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Configuration.headless;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.pollingInterval;
import static com.codeborne.selenide.Configuration.screenshots;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import entity.Todo;
import entity.Todolist;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Iterator;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.TodolistPage;

public class ClientTests {

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        baseUrl = "http://localhost:3000";
        assertionMode = AssertionMode.STRICT;
        holdBrowserOpen = false;
        timeout = 4000;
        browserSize = "1920x1080";
        screenshots = true;
        pollingInterval = 200;
        headless = false;
    }

    @BeforeMethod
    public void beforeMethod() {
        Selenide.open("");
    }

    @Test
    public void testHomePageText() {
        $(HomePage.homePageTextLocator).shouldHave(Condition.text("Home page"));
    }

    @Test
    public void testLinks() {
        int linksCount = 2;
        ElementsCollection links = $$(Header.linksLocator);
        links.shouldHave(CollectionCondition.size(linksCount));
        links.get(0).shouldHave(Condition.text("Home"));
        links.get(1).shouldHave(Condition.text("Todolist"));
    }

    @Test
    public void testTodolist() {
        Response response = RestAssured.given().baseUri("http://localhost:3001").get("/todolist");
        Todolist apiList = new Gson().fromJson(response.asString().trim(), Todolist.class);
        $$(Header.linksLocator).get(1).click();
        $(TodolistPage.todolistNameLocator).shouldHave(Condition.text(apiList.getName()));
        ElementsCollection items = $$(TodolistPage.todoItemLocator);
        items.shouldHave(CollectionCondition.size(apiList.getTodolist().size()));
        String template = "Todo name: %s, is done: %b";
        Iterator<Todo> apiListIterator=apiList.getTodolist().iterator();
        for (SelenideElement actual : items) {
            Todo expected = apiListIterator.next();
            Assert.assertEquals(actual.getText(), String.format(template, expected.getName(), expected.getDone()));
        }
    }
}
