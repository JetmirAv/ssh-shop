const { GetCategories } = require("../services/categories");

const getCategories = async (req, res, next) => {
  try {
    var categories = [];
    categories = await GetCategories();
    return res.status(200).json({ categories });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  getCategories,
};
