//products
const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/products");
const auth = require("../middleware/auth");

router.post("/", auth, controllers.create);
//router.use("/:product_id([0-9]+)/products", product_Routes);
/* Update product. */
router.patch("/:product_id([0-9]+)", auth, controllers.update);
/* GET address by id. */
router.get("/:product_id([0-9]+)", controllers.get);
/* Delete address. */
router.delete("/:product_id([0-9]+)", auth, controllers.drop);

module.exports = router;
