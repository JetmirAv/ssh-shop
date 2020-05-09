//users/:user_id/address

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/countries");
/* get countries. */
router.get("/", controllers.getCountries);

module.exports = router;
