const {
  CreateCart,
  UpdateCart,
  DeleteCart,
  GetCart,
  GetAllCarts,
} = require("../services/cart");

/**
 *
 * @param {Request} req
 * @param {Response} res
 */

const create = async (req, res, next) => {
  try {
    //assign user_id to the user_id got by params
    req.body.user_id = parseInt(req.params.user_id);
    console.log({ ...req.body });

    const cart = await CreateCart(req.body);
    return res.status(200).json({ cart });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */

const update = async (req, res, next) => {
  try {
    const cart = await UpdateCart(
      req.params.cart_id,
      req.params.user_id,
      req.body
    );
    return res.status(200).json({ cart });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const drop = async (req, res, next) => {
  try {
    const response = await DeleteCart(req.params.cart_id);
    return res.status(200).json(response);
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */
const get = async (req, res, next) => {
  try {
    return res.status(200).json({ cart: await GetCart(req.params.cart_id) });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */
const getAll = async (req, res, next) => {
  try {
    const cart = await GetAllCarts(req.params.user_id);
    return res.status(200).json({ cart });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  create,
  update,
  drop,
  get,
  getAll,
};
