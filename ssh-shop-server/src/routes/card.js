//users/:user_id/cards

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/card");

const auth = require("../middleware/auth");

/* GET card listing. */
router.get("/", auth, controllers.getCards);

/* GET card by id. */
router.get("/:card_id([0-9]+)", auth, controllers.get);

/* Create card. */
router.post("/", auth, controllers.create);

/* Update card. */
router.patch("/:card_id([0-9]+)", auth, controllers.update);

/* Delete card. */
router.delete("/:card_id([0-9]+)", auth, controllers.drop);

module.exports = router;
