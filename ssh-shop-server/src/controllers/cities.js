const {
  GetCities,
  GetCitiesFromCountry,
  GetCountryByCity,
} = require("../services/cities");

const getCities = async (req, res, next) => {
  try {
    var cities = [];
    cities = await GetCities();
    return res.status(200).json({ cities });
  } catch (err) {
    next(err);
  }
};

const getCitiesFromCountry = async (req, res, next) => {
  try {
    var cities = [];
    cities = await GetCitiesFromCountry(req.params.country_id);
    return res.status(200).json({ cities });
  } catch (err) {
    next(err);
  }
};

const getCountryByCity = async (req, res, next) => {
  try {
    console.log("brenda try-it");
    var countryName = "";
    countryName = await GetCountryByCity(req.params.city_id);
    return res.status(200).json({ countryName });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  getCities,
  getCitiesFromCountry,
  getCountryByCity,
};
