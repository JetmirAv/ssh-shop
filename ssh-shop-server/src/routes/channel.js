//channels

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/channel");

const auth = require("../middleware/auth");
const authQs = require("../middleware/auth-qs");

/*GET or CREATE channel by product_id. */
router.get("/:product_id", auth, controllers.create);
router.get("/", auth, controllers.findAll);
router.get("/:channel_id([0-9]+)/messages", auth, controllers.getMessages);
router.post("/:channel_id([0-9]+)/messages", auth, controllers.createMessage);
router.get("/:channel_id([0-9]+)/call", controllers.startCall);
router.get("/:room_id/meet", authQs, controllers.joinRoom);

module.exports = router;
