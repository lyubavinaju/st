const data = require("../todolist_data/TodolistData");
const getTodolistData = data.getTodolistData;

exports.todolistController = (req, res) => {
  res.json(getTodolistData())
}