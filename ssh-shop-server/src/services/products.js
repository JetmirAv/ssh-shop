const CustomError = require("../errors/CustomError");
const { Product, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

/**
 *
//  * @param {Number} product_id
//  * @returns Product
 */
const GetProduct = async (product_id) => {
  try {
    const product = await Product.findByPk(product_id);
    if (!product) throw new CustomError("Not found!", {}, 404);
    return product;
  } catch (err) {
    throw err;
  }
};

/**
 *
 * @param {Product} data
 * @returns Product
 */
const CreateProduct = async (data) => {
  try {
    const product = new Product({ ...data });
    await product.validate();
    await product.save();
    return product;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} product_id
//  * @param {Product} data
//  * @returns Product
 */
const UpdateProduct = async (product_id, user_id, data) => {
  try {
    const product = await Product.findOne({
      where: { id: product_id, user_id: user_id },
    });

    if (!product) throw new CustomError("Not found", {}, 404);

    const instance = new Product({ ...product.dataValues, ...data });
    await instance.validate();
    await product.update({ ...instance.dataValues });
    return product;
  } catch (err) {
    console.log({ err });

    throw err;
  }
};

/**
 *
 * @param {Product} product_id
 * @returns Boolean
 */
const DeleteProduct = async (product_id) => {
  try {
    const product = await GetProduct(product_id);
    await product.destroy();
    return true;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetProduct,
  CreateProduct,
  UpdateProduct,
  DeleteProduct,
};
