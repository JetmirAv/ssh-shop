//users/:user_id/address

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/address");
const auth = require("../middleware/auth");
/* GET address listing. */
router.get("/", controllers.getAddresses);
/* GET address by id. */
router.get("/:address_id([0-9]+)", auth, controllers.get);
/* Create address. */
router.post("/", auth, controllers.create);
/* Update address. */
router.patch("/:address_id([0-9]+)", auth, controllers.update);
/* Delete address. */
router.delete("/:address_id([0-9]+)", auth, controllers.drop);

module.exports = router;
