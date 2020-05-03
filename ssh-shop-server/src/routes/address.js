//address

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/address");
console.log("pas controllerS");
const auth = require("../middleware/auth");
/* GET address listing. */
router.get("/", controllers.func);
/* GET address by id. */
router.get("/:address_id([0-9]+)", controllers.get);
/* Create address. */
router.post("/", controllers.create);
/* Update address. */
router.patch("/:address_id([0-9]+)", auth, controllers.update);
/* Delete address. */
router.delete("/:address_id([0-9]+)", auth, controllers.drop);

module.exports = router;