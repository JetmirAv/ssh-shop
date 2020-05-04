//channel

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/channel");
console.log("pas controllerS");
const auth = require("../middleware/auth");
/* GET channel listing. */
router.get("/", controllers.func);
/*GET channel by product_id. */
router.get("/:product_id([0-9]+)", controllers.get);
console.log("para postit");
/* Create channel. */
router.post("/", controllers.create);
/* Update channel. */
router.patch("/:product_id([0-9]+)", auth, controllers.update);

module.exports = router;
