const CustomError = require("../errors/CustomError");
const Product = require("../models/mongo/products");

const GetVariant = async (product_id, combination_id) => {
  try {
    let variant = await Product.find(
      { "combinations._id": combination_id },
      { name: 1, "combinations.$": 1 }
    );

    if (!variant) throw new CustomError("Not found!", {}, 404);
    return variant;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetVariant,
};
