// wishlist
const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/wishlist");
const auth = require("../middleware/auth");


/*GET wishlist by user_id. */
router.get("/:user_id([0-9]+)", auth, controllers.get);

module.exports = router;