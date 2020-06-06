const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/cities");
/* get cities. */
router.get("/", controllers.getCities);
// Get city from cityid
router.get("/getCity/:city_id", controllers.getCity);

// Get all cities from country
router.get("/:country_id([0-9]+)", controllers.getCitiesFromCountry);

// Get city from name and countryID
router.get("/:name/:country_id", controllers.getCityFromCountryID);

//Get Country by city
router.get("/test/test/:city_id([0-9]+)", controllers.getCountryByCity);

module.exports = router;
