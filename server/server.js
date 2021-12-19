const express = require("express");
const app = express();

app.use("/todolist", require("./routes/todolistRoute"))

app.listen(3001, function () {
  console.log("Server running");
});