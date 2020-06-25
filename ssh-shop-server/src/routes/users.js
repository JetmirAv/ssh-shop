//users

const express = require("express");
const router = express.Router();
const addressRoutes = require("./address");
const cardRoutes = require("./card");
const controllers = require("../controllers/users");
const cartRoutes = require("./cart");
const wishlistRoutes = require("./wishlist");

const auth = require("../middleware/auth");

/* GET users listing. */
router.get("/", controllers.func);
/* GET user by id. */
router.get("/:user_id([0-9]+)", controllers.get);
/* Create user. */
router.post("/", controllers.create);
/* Update user. */
router.patch("/:user_id([0-9]+)", auth, controllers.update);
/* Delete user. */
router.delete("/:user_id([0-9]+)", auth, controllers.drop);

router.use("/:user_id([0-9]+)/address", addressRoutes);

router.use("/:user_id([0-9]+)/cards", cardRoutes);

router.use("/:user_id([0-9]+)/cart", cartRoutes);

router.use("/:user_id([0-9]+)/wishlist", wishlistRoutes);

module.exports = router;
