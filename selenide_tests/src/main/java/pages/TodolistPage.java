package pages;

import org.openqa.selenium.By;

public class TodolistPage {
    public static By todolistNameLocator = By.cssSelector(".todolist h1");

    public static By todoItemLocator = By.className("todoItem");
}
