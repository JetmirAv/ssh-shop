const CustomError = require("../errors/CustomError");
const { City, Country, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

const GetCities = async () => {
  try {
    var cities = await City.findAll({
      order: [["name", "ASC"]],
    });
    if (!cities) throw new CustomError("Not found!", {}, 401);
    return cities;
  } catch (err) {
    throw err;
  }
};

const GetCity = async (city_id) => {
  try {
    var cities = await City.findOne({
      where: { id: { [Op.eq]: city_id } },
    });
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

const GetCityFromCountryID = async (countryID, country_Name) => {
  try {
    let cities = await City.findAll({
      where: { name: { [Op.eq]: country_Name }, country_id: countryID },
    });
    if (cities == "") throw new CustomError("Not found!", {}, 401);
    if (!cities) throw new CustomError("Not found!", {}, 401);
    return cities;
  } catch (err) {
    throw err;
  }
};

const GetCountryByCity = async (city_id) => {
  try {
    const countryID = await City.findOne({
      where: {
        id: city_id,
      },
    });
    if (!countryID) throw new CustomError("Not found!", {}, 401);
    let countryName = await Country.findOne({
      attributes: ["id", "name"],
      where: { id: { [Op.eq]: countryID.country_id } },
    });
    if (!countryName) throw new CustomError("Not found!", {}, 401);
    return countryName;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetCities,
  GetCitiesFromCountry,
  GetCountryByCity,
  GetCity,
  GetCityFromCountryID,
};
