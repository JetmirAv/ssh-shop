const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/cities");
/* get cities. */
router.get("/", controllers.getCities);

router.get("/:country_id([0-9]+)", controllers.getCitiesFromCountry);
router.get("/test/:city_id([0-9]+)", controllers.getCountryByCity);

module.exports = router;
