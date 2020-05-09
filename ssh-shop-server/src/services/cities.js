const CustomError = require("../errors/CustomError");
const { City } = require("../models/sequelize");

const GetCities = async () => {
  try {
    var cities = await City.findAll({});
    if (!cities) throw new CustomError("Not found!", {}, 401);
    return cities;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetCities,
};
