const { GetCities } = require("../services/cities");

const getCities = async (req, res, next) => {
  try {
    var cities = [];
    cities = await GetCities();
    return res.status(200).json({ cities });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  getCities,
};
