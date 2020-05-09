const { GetCountries } = require("../services/countries");

const getCountries = async (req, res, next) => {
  try {
    var countries = [];
    countries = await GetCountries();
    return res.status(200).json({ countries });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  getCountries,
};
