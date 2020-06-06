//products
const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/products");
const auth = require("../middleware/auth");

/* GET products. */
router.get("/", controllers.getProducts);
/* Create products*/
router.post("/", auth, controllers.create);
/* Update product. */
router.patch("/:product_id([A-Za-z0-9]+)", auth, controllers.update);
/* GET product by id. */
router.get("/:product_id([A-Za-z0-9]+)", controllers.get);
/* Delete product. */
router.delete("/:product_id([A-Za-z0-9]+)", auth, controllers.drop);

module.exports = router;
