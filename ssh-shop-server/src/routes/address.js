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
console.log("Para post it");
router.post("/", controllers.create);
console.log("pas postit");
/* Update address. */
console.log("para patch");
router.patch("/:address_id([0-9]+)", auth, controllers.update);
console.log("pas patch");
/* Delete user. */
//router.delete("/:user_id([0-9]+)", auth, controllers.drop);

module.exports = router;
