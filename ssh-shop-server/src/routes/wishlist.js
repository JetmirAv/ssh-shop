// wishlist
const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/wishlist");


/*GET wishlist by product_id. */
router.get("/:product_id([0-9]+)", auth, controllers.get);

module.exports = router;