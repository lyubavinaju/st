module.exports = {
  getTodolistData: function () {
    const listName = "TodoList1";
    const todo1 = {
      name: "todo1",
      done: false
    };

    const todo2 = {
      name: "todo2",
      done: false
    };

    const todo3 = {
      name: "todo3",
      done: true
    };
    return {
      name: listName,
      todolist: [todo1, todo2, todo3]
    };
  }
}

