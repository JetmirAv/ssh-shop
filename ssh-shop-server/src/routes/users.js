//users

const express = require("express");
const router = express.Router();

const controllers = require("../controllers/users");
const auth = require("../middleware/auth");

/* GET users listing. */
router.get("/", controllers.func);
/* Create user. */
router.post("/", controllers.create);
/* Update user. */
router.patch("/:user_id([0-9]+)", auth, controllers.func);
/* Delete user. */
router.delete("/:user_id([0-9]+)", auth, controllers.func);

module.exports = router;
