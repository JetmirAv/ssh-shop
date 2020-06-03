const { GetCountries, GetCountryByName } = require("../services/countries");

const getCountries = async (req, res, next) => {
  try {
    var countries = [];
    countries = await GetCountries();
    return res.status(200).json({ countries });
  } catch (err) {
    next(err);
  }
};

const getCountry = async (req, res, next) => {
  try {
    var country = "";
    country = await GetCountryByName(req.params.name);
    return res.status(200).json({ country });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  getCountries,
  getCountry,
};
