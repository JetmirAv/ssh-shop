const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/categories");

/* get categories. */
router.get("/", controllers.getCategories);

module.exports = router;
