const data = require("../todolist_data/TodolistData")
const getTodolistData = data.getTodolistData;

test('Get data test', () => {
  const expectedListName = "TodoList1";
  const expectedTodoItemsCount = 3;
  const actualResult = getTodolistData();

  expect(actualResult.name).toEqual(expectedListName);
  expect(actualResult.todolist).toHaveLength(expectedTodoItemsCount)
});
