const {test, expect} = require('@playwright/test');

test('basic test', async ({page}) => {
  const URL = 'http://localhost:3000/';
  await page.goto(URL);

  const homePageText = page.locator('.home');
  await expect(homePageText).toHaveText('Home page');

  const todolistSelector = 'header a:last-child';
  const todoListLink = page.locator(todolistSelector);
  await expect(todoListLink).toHaveText('Todolist');

  await page.click(todolistSelector);

  const listNameLocator = page.locator('h1');
  const expectedListName = 'TodoList1';
  await expect(listNameLocator).toHaveText(expectedListName);

});