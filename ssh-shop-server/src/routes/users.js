//users

const express = require("express");
const router = express.Router();
const address_Routes = require("./address");
const card_Routes = require("./card");
const controllers = require("../controllers/users");
const auth = require("../middleware/auth");

/* GET users listing. */
router.get("/", controllers.func);
/* GET user by id. */
router.get("/:user_id([0-9]+)", controllers.get);
/* Create user. */
router.post("/", controllers.create);
/* Update user. */
router.patch("/:user_id([0-9]+)", auth, controllers.update);
/* Delete user. */
router.delete("/:user_id([0-9]+)", auth, controllers.drop);

router.use("/:user_id([0-9]+)/address", address_Routes);

router.use("/:user_id([0-9]+)/card", card_Routes);

module.exports = router;
