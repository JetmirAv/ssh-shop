const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/cities");
/* get cities. */
router.get("/", controllers.getCities);

router.get("/getCity/:city_id", controllers.getCity);

router.get("/:country_id([0-9]+)", controllers.getCitiesFromCountry);

router.get("/:name/:country_id", controllers.getCityFromCountryID);
//Get Country from City

router.get("/test/test/:city_id([0-9]+)", controllers.getCountryByCity);

module.exports = router;
