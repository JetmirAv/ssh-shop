const CustomError = require("../errors/CustomError");
const { City, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

const GetCities = async () => {
  try {
    var cities = await City.findAll({});
    if (!cities) throw new CustomError("Not found!", {}, 401);
    return cities;
  } catch (err) {
    throw err;
  }
};

const GetCitiesFromCountry = async (country_id) => {
  try {
    let cities = await City.findAll({
      where: { country_id: { [Op.eq]: country_id } },
    });
    if (!cities) throw new CustomError("Not found!", {}, 401);
    return cities;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetCities,
  GetCitiesFromCountry,
};
