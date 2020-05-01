//address

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/address");
console.log("pas controllerS");
const auth = require("../middleware/auth");
/* GET users listing. */
//router.get("/", controllers.func);
/* GET user by id. */
//router.get("/:user_id([0-9]+)", controllers.get);
/* Create user. */
router.post("/", controllers.create);
/* Update address. */
router.patch("/:address_id([0-9]+)", controllers.update);
/* Delete user. */
//router.delete("/:user_id([0-9]+)", auth, controllers.drop);
module.exports = router;
