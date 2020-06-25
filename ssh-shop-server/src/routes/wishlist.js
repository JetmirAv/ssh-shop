//users/:user_id/cart

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/wishlist");

const auth = require("../middleware/auth");


/* GET all carts. */
router.get("/", controllers.getAll);

module.exports = router;
