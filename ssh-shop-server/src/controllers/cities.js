const {
  GetCities,
  GetCitiesFromCountry,
  GetCountryByCity,
  GetCity,
  GetCityFromCountryID,
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
const getCity = async (req, res, next) => {
  try {
    var cities = [];
    cities = await GetCity(req.params.city_id);
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

const getCityFromCountryID = async (req, res, next) => {
  try {
    var cities = [];
    cities = await GetCityFromCountryID(req.params.country_id, req.params.name);
    return res.status(200).json({ cities });
  } catch (err) {
    next(err);
  }
};

const getCountryByCity = async (req, res, next) => {
  try {
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
  getCity,
  getCityFromCountryID,
};
