//users/:user_id/address

const express = require("express");
const router = express.Router({ mergeParams: true });
const controllers = require("../controllers/countries");
const citiesRoutes = require("./cities");
/* get countries. */
router.get("/", controllers.getCountries);
router.use("/cities", citiesRoutes);
module.exports = router;
