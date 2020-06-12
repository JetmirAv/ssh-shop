const CustomError = require("../errors/CustomError");
const { Cart, Sequelize } = require("../models/sequelize");
const Product = require("../models/mongo/products");
const Op = Sequelize.Op;

/**
 *
//  * @param {Number} cart_id
//  * @returns Cart
 */
const GetCart = async (cart_id) => {
  try {
    const cart = await Cart.findByPk(cart_id);
    if (!cart) throw new CustomError("Not found!", {}, 401);
    return cart;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} cart_id
//  * @returns Cart
 */
const GetAllCarts = async (user_id) => {
  try {
    const cart = await Cart.findAll({
      where: {
        user_id: user_id,
      },
    });

    let productIds = cart.map((rec) => rec.product_id);
    let variantIds = cart.map((rec) => rec.variant_id);

    const products = await Product.find({ _id: { $in: productIds } });

    let response = [];

    for (let product of products) {
      for (let combination of product.combinations) {
        if (variantIds.includes(combination.id)) {
          response.push({
            name: product.name,
            product_id: product.id,
            combination,
          });
          break;
        }
      }
    }

    for (let resp in response) {
      for (let c of cart) {
        if (c.product_id === response[resp].product_id) {
          response[resp] = {
            ...response[resp],
            quantity: c.quantity,
            id: c.id,
          };
          break;
        }
      }
    }

    if (!response) throw new CustomError("Not found!", {}, 401);
    return response;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} user_id
//  * @returns Cart
 */

const CreateCart = async (data) => {
  try {
    const cart = new Cart({ ...data });
    await cart.validate();
    await cart.save();
    return cart;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} cart_id
//  * @param {Cart} data
//  * @returns Cart
 */
const UpdateCart = async (cart_id, user_id, data) => {
  try {
    const cart = await Cart.findOne({
      where: {
        id: cart_id,
        user_id: user_id,
      },
    });
    if (cart) {
      const instance = new Cart({ ...cart.dataValues, ...data });
      await instance.validate();
      await cart.update({
        ...instance.dataValues,
      });
      return cart;
    } else {
      throw new CustomError("No combination between user and cart", {}, 404);
    }
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Cart} cart
//  * @returns Boolean
//  */
const DeleteCart = async (cart_id) => {
  try {
    const cart = await GetCart(cart_id);
    await cart.destroy();
    return true;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  CreateCart,
  UpdateCart,
  DeleteCart,
  GetCart,
  GetAllCarts,
};
