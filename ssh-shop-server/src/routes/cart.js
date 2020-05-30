//users/:user_id/cart

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/cart");

const auth = require("../middleware/auth");

/* GET cart by id. */
router.get("/:cart_id([0-9]+)", auth, controllers.get);

/* Create cart. */
router.post("/", auth, controllers.create);

/* Update cart. */
router.patch("/:cart_id([0-9]+)", auth, controllers.update);

/* Delete cart. */
router.delete("/:cart_id([0-9]+)", auth, controllers.drop);

module.exports = router;
