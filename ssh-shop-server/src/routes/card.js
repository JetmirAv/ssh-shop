//card

const express = require("express");

const router = express.Router({ mergeParams: true });

const controllers = require("../controllers/card");

console.log("pas controllerS");

const auth = require("../middleware/auth");

/* GET card listing. */

router.get("/", controllers.func);

/* GET card by id. */

router.get("/:card_id([0-9]+)", controllers.get);

/* Create card. */

router.post("/", controllers.create);

/* Update card. */

router.patch("/:card_id([0-9]+)", auth, controllers.update);

/* Delete card. */

router.delete("/:card_id([0-9]+)", auth, controllers.drop);

module.exports = router;
