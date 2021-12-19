const express = require("express");
router = express.Router();
todolistRoute = require("../controllers/todolistController");
router.get("/", todolistRoute.todolistController);
module.exports = router;