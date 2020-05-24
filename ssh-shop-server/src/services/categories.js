const CustomError = require("../errors/CustomError");
const { Category, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

const GetCategories = async () => {
  try {
    var categories = await Category.findAll({});
    if (!categories) throw new CustomError("Not found!", {}, 401);
    return categories;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetCategories,
};
