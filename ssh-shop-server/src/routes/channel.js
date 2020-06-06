//channels

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/channel");

const auth = require("../middleware/auth");

/*GET or CREATE channel by product_id. */
router.get("/:product_id([0-9]+)", auth, controllers.create);
router.get("/", auth, controllers.findAll);
router.get("/:channel_id([0-9]+)/messages", auth, controllers.getMessages);
router.post("/:channel_id([0-9]+)/messages", auth, controllers.createMessage);

module.exports = router;
