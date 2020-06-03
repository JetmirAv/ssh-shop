const CustomError = require("../errors/CustomError");
const { Country } = require("../models/sequelize");

const GetCountries = async () => {
  try {
    var countries = await Country.findAll({
      order: [["name", "ASC"]],
    });
    if (!countries) throw new CustomError("Not found!", {}, 401);
    return countries;
  } catch (err) {
    throw err;
  }
};

const GetCountryByName = async (countryName) => {
  try {
    const countryID = await Country.findOne({
      where: {
        name: countryName,
      },
    });
    if (!countryID) throw new CustomError("Not found!", {}, 401);
    return countryID;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetCountries,
  GetCountryByName,
};
