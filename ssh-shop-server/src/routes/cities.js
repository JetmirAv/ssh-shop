const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/cities");
/* get cities. */
router.get("/", controllers.getCities);

module.exports = router;
